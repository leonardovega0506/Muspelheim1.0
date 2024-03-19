package mx.com.ananda.midgard.service.implementation;

import mx.com.ananda.midgard.model.dto.ClienteDTO;
import mx.com.ananda.midgard.model.dto.OrdenVentaDTO;
import mx.com.ananda.midgard.model.dto.VendedorDTO;
import mx.com.ananda.midgard.model.entity.ClienteModel;
import mx.com.ananda.midgard.model.entity.OrdenVentaModel;
import mx.com.ananda.midgard.model.entity.VendedorModel;
import mx.com.ananda.midgard.repositories.IClienteRepository;
import mx.com.ananda.midgard.repositories.IOrdenVentaRepository;
import mx.com.ananda.midgard.repositories.IVendedorRepository;
import mx.com.ananda.midgard.response.ListClienteResponse;
import mx.com.ananda.midgard.response.ListOrdenVentaResponse;
import mx.com.ananda.midgard.response.ListVendedorResponse;
import mx.com.ananda.midgard.response.VendedorResponse;
import mx.com.ananda.midgard.service.interfaces.IVendedorService;
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
public class VendedorServiceImpl implements IVendedorService {

    @Autowired
    private IVendedorRepository iVendedor;

    @Autowired
    private IClienteRepository iCliente;

    @Autowired
    private IOrdenVentaRepository iOrden;

    @Autowired
    private MapperServiceImpl modelMapper;



    @Override
    public ListVendedorResponse findAll(int numPage, int sizePage, String orderBy, String sortDir) {
        ListVendedorResponse response = new ListVendedorResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<VendedorModel> vendedores = iVendedor.findAll(pageable);
        List<VendedorModel> listVendedores = vendedores.getContent();
        List<VendedorDTO> contenido = listVendedores.stream().map(vendedor -> modelMapper.mapear(vendedor, VendedorDTO.class)).collect(Collectors.toList());
        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Successful response");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(vendedores.getNumber());
        response.setSizePage(vendedores.getSize());
        response.setTotalElements(vendedores.getTotalElements());
        response.setTotalPages(vendedores.getTotalPages());
        response.setIsLast(vendedores.isLast());

        return response;
    }

    @Override
    public ListVendedorResponse findAllByName(int numPage, int sizePage, String orderBy, String sortDir, String name) {
        ListVendedorResponse response = new ListVendedorResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<VendedorModel> vendedores = iVendedor.findBySlpNameLike(name,pageable);
        List<VendedorModel> listVendedores = vendedores.getContent();
        List<VendedorDTO> contenido = listVendedores.stream().map(vendedor -> modelMapper.mapear(vendedor, VendedorDTO.class)).collect(Collectors.toList());
        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Successful response");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(vendedores.getNumber());
        response.setSizePage(vendedores.getSize());
        response.setTotalElements(vendedores.getTotalElements());
        response.setTotalPages(vendedores.getTotalPages());
        response.setIsLast(vendedores.isLast());

        return response;
    }

    @Override
    public VendedorResponse findById(Long idVendedor) {
        VendedorDTO vendedorBuscado = modelMapper.mapear(iVendedor.findById(idVendedor).orElseThrow(),VendedorDTO.class);
        VendedorResponse response = new VendedorResponse();
        response.setResponse(vendedorBuscado);
        response.setCode(0);
        response.setMessage("Successful response");
        return response;
    }

    @Override
    public VendedorResponse findByCodigo(int slpCode) {
        VendedorDTO vendedorBuscado = modelMapper.mapear(iVendedor.findBySlpCode(slpCode).orElseThrow(),VendedorDTO.class);
        VendedorResponse response = new VendedorResponse();
        response.setResponse(vendedorBuscado);
        response.setCode(0);
        response.setMessage("Successful response");
        return response;
    }

    @Override
    public ListClienteResponse findClienteByVendedor(int numPage, int sizePage, String orderBy, String sortDir, int slpCode) {
        ListClienteResponse response = new ListClienteResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<ClienteModel> clientes = iCliente.findByVendedor_SlpCode(slpCode,pageable);
        List<ClienteModel> listClientes = clientes.getContent();
        List<ClienteDTO> contenido = listClientes.stream().map(vendedor -> modelMapper.mapear(vendedor, ClienteDTO.class)).collect(Collectors.toList());
        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Successful response");
        }
        else{
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
    public ListOrdenVentaResponse findOrdenVendedor(int numPage, int sizePage, String orderBy, String sortDir, int slpCode) {
        ListOrdenVentaResponse response = new ListOrdenVentaResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<OrdenVentaModel> ordenes = iOrden.findBySlpCode(slpCode,pageable);
        List<OrdenVentaModel> listOrden = ordenes.getContent();
        List<OrdenVentaDTO> contenido = listOrden.stream().map(orden -> modelMapper.mapear(orden, OrdenVentaDTO.class)).collect(Collectors.toList());
        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Successful response");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(ordenes.getNumber());
        response.setSizePage(ordenes.getSize());
        response.setTotalElements(ordenes.getTotalElements());
        response.setTotalPages(ordenes.getTotalPages());
        response.setIsLast(ordenes.isLast());

        return response;

    }

    @Override
    public ListVendedorResponse findAllByGroupCode(int numPage, int sizePage, String orderBy, String sortDir, int groupCode) {
        ListVendedorResponse response = new ListVendedorResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage,sizePage,sort);
        Page<VendedorModel> vendedores = iVendedor.findByGroupCode(groupCode,pageable);
        List<VendedorModel> listVendedores = vendedores.getContent();
        List<VendedorDTO> contenido = listVendedores.stream().map(vendedor -> modelMapper.mapear(vendedor, VendedorDTO.class)).collect(Collectors.toList());
        if(contenido.size()>0){
            response.setCode(0);
            response.setMessage("Successful response");
        }
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        response.setContent(contenido);
        response.setNumPage(vendedores.getNumber());
        response.setSizePage(vendedores.getSize());
        response.setTotalElements(vendedores.getTotalElements());
        response.setTotalPages(vendedores.getTotalPages());
        response.setIsLast(vendedores.isLast());

        return response;
    }
}
