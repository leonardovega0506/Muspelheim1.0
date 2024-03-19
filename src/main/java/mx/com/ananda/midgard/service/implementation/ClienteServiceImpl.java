package mx.com.ananda.midgard.service.implementation;

import mx.com.ananda.midgard.model.dto.ClienteDTO;
import mx.com.ananda.midgard.model.dto.OrdenVentaDTO;
import mx.com.ananda.midgard.model.entity.ClienteModel;
import mx.com.ananda.midgard.model.entity.OrdenVentaModel;
import mx.com.ananda.midgard.repositories.IClienteRepository;
import mx.com.ananda.midgard.repositories.IOrdenVentaRepository;
import mx.com.ananda.midgard.response.ClienteResponse;
import mx.com.ananda.midgard.response.ListClienteResponse;
import mx.com.ananda.midgard.response.ListOrdenVentaResponse;
import mx.com.ananda.midgard.service.interfaces.IClienteService;
import mx.com.ananda.midgard.service.interfaces.IOrdenVentaService;
import mx.com.ananda.midgard.util.MapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteRepository iCliente;

    @Autowired
    private MapperServiceImpl modelMapper;

    @Autowired
    private IOrdenVentaRepository iOrdenVenta;

    @Override
    public ListClienteResponse findAll(int numPage, int sizePage, String orderBy, String sortDir) {
        ListClienteResponse response = new ListClienteResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<ClienteModel> clientes = iCliente.findAll(pageable);
        List<ClienteModel> listaCliente = clientes.getContent();
        List<ClienteDTO> contenido = listaCliente.stream().map(cliente -> modelMapper.mapear(cliente,ClienteDTO.class)).collect(Collectors.toList());

        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Successful response");
        }
        else {
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
    public ListClienteResponse findAllByName(int numPage, int sizePage, String orderBy, String sortDir, String cardName) {
        ListClienteResponse response = new ListClienteResponse();
        if(cardName!=null){
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
            Pageable pageable = PageRequest.of(numPage,sizePage,sort);
            Page<ClienteModel> clientes = iCliente.findByCardName(cardName,pageable);
            List<ClienteModel> listaCliente = clientes.getContent();
            List<ClienteDTO> contenido = listaCliente.stream().map(cliente -> modelMapper.mapear(cliente,ClienteDTO.class)).collect(Collectors.toList());

            if(contenido.size()>0){
                response.setCode(0);
                response.setMessage("Successful response");
            }
            else {
                response.setCode(1);
                response.setMessage("Not Found");
            }
            response.setContent(contenido);
            response.setNumPage(clientes.getNumber());
            response.setSizePage(clientes.getSize());
            response.setTotalElements(clientes.getTotalElements());
            response.setTotalPages(clientes.getTotalPages());
            response.setIsLast(clientes.isLast());
        }
        else{
            response.setCode(2);
            response.setMessage("Bad request");
        }
        return response;
    }

    @Override
    public ClienteResponse findClienteByCardCode(Integer cardCode) {
        ClienteDTO clienteBuscado = modelMapper.mapear(iCliente.findByCardCode(cardCode).orElseThrow(),ClienteDTO.class);
        ClienteResponse response = new ClienteResponse();
        response.setCode(0);
        response.setMessage("Successful response");
        response.setResponse(clienteBuscado);
        return response;
    }

    @Override
    public ClienteResponse findClienteById(Long id) {
        ClienteDTO clienteBuscado = modelMapper.mapear(iCliente.findById(id).orElseThrow(),ClienteDTO.class);
        ClienteResponse response = new ClienteResponse();
        response.setCode(0);
        response.setMessage("Successful response");
        response.setResponse(clienteBuscado);
        return response;
    }

    @Override
    public ListOrdenVentaResponse findOrdenVentaCliente(int numPage, int sizePage, String orderBy, String sortDir, Long idCliente) {
        ListOrdenVentaResponse response = new ListOrdenVentaResponse();
        if(idCliente!=0){
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
            Pageable pageable = PageRequest.of(numPage,sizePage,sort);
            Page<OrdenVentaModel> ordenes = iOrdenVenta.findByCliente_IdCliente(idCliente,pageable);
            List<OrdenVentaModel> listaOrdenes = ordenes.getContent();
            List<OrdenVentaDTO> contenido = listaOrdenes.stream().map(orden -> modelMapper.mapear(orden,OrdenVentaDTO.class)).collect(Collectors.toList());

            if(contenido.size()>0){
                response.setCode(0);
                response.setMessage("Successful response");
            }
            else {
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
        else{
            response.setCode(2);
            response.setMessage("Bad request");
        }
        return response;
    }
}
