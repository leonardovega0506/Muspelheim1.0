package mx.com.ananda.midgard.service.implementation;

import lombok.extern.slf4j.Slf4j;
import mx.com.ananda.midgard.model.dto.ClienteDTO;
import mx.com.ananda.midgard.model.dto.OrdenVentaDTO;
import mx.com.ananda.midgard.model.entity.ClienteModel;
import mx.com.ananda.midgard.model.entity.OrdenVentaModel;
import mx.com.ananda.midgard.model.sap.model.OrderSAP;
import mx.com.ananda.midgard.model.sap.model.VendorPaymentSAP;
import mx.com.ananda.midgard.model.sap.response.OrderResponseMD;
import mx.com.ananda.midgard.model.sap.response.VendorPaymentResponseMD;
import mx.com.ananda.midgard.repositories.IClienteRepository;
import mx.com.ananda.midgard.repositories.IOrdenVentaRepository;
import mx.com.ananda.midgard.response.ClienteResponse;
import mx.com.ananda.midgard.response.ListClienteResponse;
import mx.com.ananda.midgard.response.ListOrdenVentaResponse;
import mx.com.ananda.midgard.service.interfaces.IClienteService;
import mx.com.ananda.midgard.util.MapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteRepository iCliente;

    @Autowired
    private IOrdenVentaRepository iOrdenVenta;

    @Autowired
    private MapperServiceImpl modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.external.service.base-url}")
    private String basePath;


    @Override
    public ListClienteResponse findAll(int numPage, int sizePage, String orderBy, String sortDir) {
        ListClienteResponse response = new ListClienteResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<ClienteModel> clientes = iCliente.findAll(pageable);
        List<ClienteModel> listaCliente = clientes.getContent();
        List<ClienteDTO> contenido = listaCliente.stream().map(cliente -> modelMapper.mapear(cliente, ClienteDTO.class)).collect(Collectors.toList());

        if (contenido.size() > 0) {
            response.setCode(0);
            response.setMessage("Successful response");
        } else {
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(clientes.getNumber());
        response.setSizePage(clientes.getSize());
        response.setTotalElements(clientes.getTotalElements());
        response.setTotalPages(clientes.getTotalPages());
        response.setIsLast(clientes.isLast());
        return response;
    }

    @Override
    public ListClienteResponse findAllByName(String cardName) {
        ListClienteResponse response = new ListClienteResponse();
        if (cardName != "") {
            log.info("CardName: {}",cardName);
            List<ClienteModel> clientes = iCliente.findByCardNameContains(cardName);
            List<ClienteDTO> contenido = clientes.stream().map(cliente -> modelMapper.mapear(cliente, ClienteDTO.class)).collect(Collectors.toList());

            if (contenido.size() > 0) {
                response.setCode(0);
                response.setMessage("Successful response");
            } else {
                response.setCode(1);
                response.setMessage("Not Found");
            }
            response.setContent(contenido);
        } else {
            response.setCode(2);
            response.setMessage("Bad request");
        }
        return response;
    }

    @Override
    public ClienteResponse findClienteByCardCode(String cardCode) {
        ClienteResponse response = new ClienteResponse();
        if (cardCode != "") {
            Optional<ClienteModel> oCliente = iCliente.findByCardCode(cardCode);

            if (oCliente.isEmpty()) {
                try {
                    VendorPaymentResponseMD vendor = restTemplate.getForObject(basePath + "/cliente/cardCode?cardCode=" + cardCode, VendorPaymentResponseMD.class);
                    if (vendor.getCode() == 0) {
                        VendorPaymentSAP clienteSap = vendor.getResponse();
                        ClienteDTO clienteBuscado = modelMapper.mapear(clienteSap, ClienteDTO.class);
                        log.info("Cliente: {}", clienteBuscado);
                        ClienteModel clienteNuevo = iCliente.save(modelMapper.mapear(clienteBuscado, ClienteModel.class));
                        if (clienteNuevo.getCardCode() != null || clienteNuevo.getCardCode() != "") {
                            response.setCode(0);
                            response.setMessage("Successful response");
                            response.setResponse(modelMapper.mapear(clienteNuevo, ClienteDTO.class));
                        } else {
                            response.setCode(1);
                            response.setMessage("Not Found");
                        }
                    } else if (vendor.getCode() == 3) {
                        response.setCode(1);
                        response.setMessage("Not Found");
                    }
                } catch (HttpClientErrorException.NotFound ex) {
                    response.setCode(1);
                    response.setMessage(ex.getMessage());
                }

            } else {
                response.setCode(0);
                response.setMessage("Successful response");
                response.setResponse(modelMapper.mapear(oCliente.get(), ClienteDTO.class));
            }
        } else {
            response.setCode(2);
            response.setMessage("Bad Request");
        }
        return response;
    }

    @Override
    public ClienteResponse findClienteById(Long id) {
        Optional<ClienteModel> clienteBuscado = iCliente.findById(id);
        ClienteResponse response = new ClienteResponse();
        if (clienteBuscado.isEmpty()) {
            response.setCode(1);
            response.setMessage("Not Found");
        } else {
            response.setCode(0);
            response.setMessage("Successful response");
            response.setResponse(modelMapper.mapear(clienteBuscado, ClienteDTO.class));
        }
        return response;
    }

    @Override
    public ListOrdenVentaResponse findOrdenVentaCliente(int numPage, int sizePage, String orderBy, String sortDir, Long idCliente) {
        ListOrdenVentaResponse response = new ListOrdenVentaResponse();
        if (idCliente != 0) {
            Optional<ClienteModel> oCliente = iCliente.findById(idCliente);
            if (oCliente.isEmpty()) {
                response.setCode(1);
                response.setMessage("Not Found");
            } else {
                ClienteModel clienteBuscado = oCliente.get();
                OrderResponseMD order = restTemplate.getForObject(basePath + "/orden/cliente?cardCode=" + clienteBuscado.getCardCode(), OrderResponseMD.class);
                if (clienteBuscado.getOrdenes().size() > 0 && clienteBuscado.getOrdenes().size() != order.getListResponse().size()) {
                    iOrdenVenta.deleteByCliente(clienteBuscado);
                    List<OrderSAP> orders = order.getListResponse();
                    iOrdenVenta.saveAll(orders.stream().map(orden -> modelMapper.mapear(orden, OrdenVentaModel.class)).collect(Collectors.toList()));
                    //Paginacion y obtencion de la lista
                    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
                    Pageable pageable = PageRequest.of(numPage, sizePage, sort);
                    Page<OrdenVentaModel> ordenes = iOrdenVenta.findByCliente_IdCliente(idCliente, pageable);
                    List<OrdenVentaModel> listaOrdenesDTO = ordenes.getContent();
                    List<OrdenVentaDTO> contenido = listaOrdenesDTO.stream().map(orden -> modelMapper.mapear(orden, OrdenVentaDTO.class)).collect(Collectors.toList());
                    if (contenido.size() > 0) {
                        response.setCode(0);
                        response.setMessage("Successful response");
                    } else {
                        response.setCode(1);
                        response.setMessage("Not Found");
                    }
                    response.setContent(contenido);
                    response.setNumPage(ordenes.getNumber());
                    response.setSizePage(ordenes.getSize());
                    response.setTotalElements(ordenes.getTotalElements());
                    response.setTotalPages(ordenes.getTotalPages());
                    response.setIsLast(ordenes.isLast());

                }
                else if(clienteBuscado.getOrdenes().size() > 0 && clienteBuscado.getOrdenes().size() == order.getListResponse().size()){
                    //Paginacion y obtencion de la lista
                    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
                    Pageable pageable = PageRequest.of(numPage, sizePage, sort);
                    Page<OrdenVentaModel> ordenes = iOrdenVenta.findByCliente_IdCliente(idCliente, pageable);
                    List<OrdenVentaModel> listaOrdenesDTO = ordenes.getContent();
                    List<OrdenVentaDTO> contenido = listaOrdenesDTO.stream().map(orden -> modelMapper.mapear(orden, OrdenVentaDTO.class)).collect(Collectors.toList());
                    if (contenido.size() > 0) {
                        response.setCode(0);
                        response.setMessage("Successful response");
                    } else {
                        response.setCode(1);
                        response.setMessage("Not Found");
                    }
                    response.setContent(contenido);
                    response.setNumPage(ordenes.getNumber());
                    response.setSizePage(ordenes.getSize());
                    response.setTotalElements(ordenes.getTotalElements());
                    response.setTotalPages(ordenes.getTotalPages());
                    response.setIsLast(ordenes.isLast());
                }
                else {
                    order = restTemplate.getForObject(basePath + "/orden/cliente?cardCode=" + clienteBuscado.getCardCode(), OrderResponseMD.class);
                    List<OrderSAP> orders = order.getListResponse();
                    List<OrdenVentaModel> ordenesNuevas =iOrdenVenta.saveAll(orders.stream().map(orden -> {
                        OrdenVentaModel ordenVentaModel = modelMapper.mapear(orden, OrdenVentaModel.class);
                        ordenVentaModel.setCliente(clienteBuscado);
                        return ordenVentaModel;
                    }).collect(Collectors.toList()));
                    clienteBuscado.setOrdenes(ordenesNuevas);
                    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
                    Pageable pageable = PageRequest.of(numPage, sizePage, sort);
                    Page<OrdenVentaModel> ordenes = iOrdenVenta.findByCliente_IdCliente(idCliente, pageable);
                    List<OrdenVentaModel> listaOrdenesDTO = ordenes.getContent();
                    List<OrdenVentaDTO> contenido = listaOrdenesDTO.stream().map(orden -> modelMapper.mapear(orden, OrdenVentaDTO.class)).collect(Collectors.toList());
                    log.info("Ordenes: {}",contenido);
                    if (contenido.size() > 0) {
                        response.setCode(0);
                        response.setMessage("Successful response");
                    } else {
                        response.setCode(1);
                        response.setMessage("Not Found");
                    }
                    response.setContent(contenido);
                    response.setNumPage(ordenes.getNumber());
                    response.setSizePage(ordenes.getSize());
                    response.setTotalElements(ordenes.getTotalElements());
                    response.setTotalPages(ordenes.getTotalPages());
                    response.setIsLast(ordenes.isLast());
                }
            }
            /*
            */
        }
        else{
            response.setCode(2);
            response.setMessage("Bad Request");
        }
        return response;
    }
}
