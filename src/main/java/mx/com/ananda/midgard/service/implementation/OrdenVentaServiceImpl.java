package mx.com.ananda.midgard.service.implementation;

import mx.com.ananda.midgard.model.dto.OrdenVentaDTO;
import mx.com.ananda.midgard.model.entity.OrdenVentaModel;
import mx.com.ananda.midgard.repositories.IOrdenVentaRepository;
import mx.com.ananda.midgard.response.ListOrdenSAPResponse;
import mx.com.ananda.midgard.response.ListOrdenVentaResponse;
import mx.com.ananda.midgard.response.OrdenVentaResponse;
import mx.com.ananda.midgard.service.interfaces.IOrdenVentaService;
import mx.com.ananda.midgard.util.MapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenVentaServiceImpl implements IOrdenVentaService {

    @Autowired
    private IOrdenVentaRepository iOrdenVenta;

    @Autowired
    private MapperServiceImpl modelMapper;

    @Override
    public ListOrdenVentaResponse findAll(int numPage, int sizePage, String orderBy, String sortDir) {
        ListOrdenVentaResponse response = new ListOrdenVentaResponse();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<OrdenVentaModel> ordenes = iOrdenVenta.findAll(pageable);
        List<OrdenVentaModel> listaOrdenes = ordenes.getContent();
        List<OrdenVentaDTO> contenido = listaOrdenes.stream().map(orden -> modelMapper.mapear(orden, OrdenVentaDTO.class)).collect(Collectors.toList());

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

        return response;
    }

    @Override
    public ListOrdenVentaResponse findAllDate(int numPage, int sizePage, String orderBy, String sortDir, LocalDate date) {
        ListOrdenVentaResponse response = new ListOrdenVentaResponse();
        if(date!=null) {
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
            Pageable pageable = PageRequest.of(numPage, sizePage, sort);
            Page<OrdenVentaModel> ordenes = iOrdenVenta.findByDocDate(date.atStartOfDay(), pageable);
            List<OrdenVentaModel> listaOrdenes = ordenes.getContent();
            List<OrdenVentaDTO> contenido = listaOrdenes.stream().map(orden -> modelMapper.mapear(orden, OrdenVentaDTO.class)).collect(Collectors.toList());

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
        else{
            response.setCode(1);
            response.setMessage("Not Found");
        }
        return response;
    }

    @Override
    public OrdenVentaResponse findById(Long idOrden) {
        OrdenVentaDTO ordenBuscada = modelMapper.mapear(iOrdenVenta.findById(idOrden).orElseThrow(),OrdenVentaDTO.class);
        OrdenVentaResponse response = new OrdenVentaResponse();
        response.setResponse(ordenBuscada);
        response.setCode(0);
        response.setMessage("Successful response");
        return response;
    }

    @Override
    public OrdenVentaResponse findByDocNum(int docNum) {
        OrdenVentaResponse response = new OrdenVentaResponse();
        if(docNum !=0){
            OrdenVentaDTO ordenBuscada = modelMapper.mapear(iOrdenVenta.findByDocNum((long) docNum).orElseThrow(),OrdenVentaDTO.class);
            response.setResponse(ordenBuscada);
            response.setCode(0);
            response.setMessage("Successful response");
        }
        else{
            response.setCode(2);
            response.setMessage("Bad request");
        }

        return response;
    }

    @Override
    public OrdenVentaResponse findByFactura(String ref1) {
        OrdenVentaResponse response = new OrdenVentaResponse();
        if(ref1 !=null){
            OrdenVentaDTO ordenBuscada = modelMapper.mapear(iOrdenVenta.findByRef1(ref1).orElseThrow(),OrdenVentaDTO.class);
            response.setResponse(ordenBuscada);
            response.setCode(0);
            response.setMessage("Successful response");
        }
        else{
            response.setCode(2);
            response.setMessage("Bad request");
        }

        return response;

    }

    @Override
    public OrdenVentaResponse updateEstatusOrden(long docNum, int estatus) {
        OrdenVentaResponse response = new OrdenVentaResponse();
        if(docNum !=0){
            OrdenVentaDTO ordenBuscada = modelMapper.mapear(iOrdenVenta.findByDocNum((long) docNum).orElseThrow(),OrdenVentaDTO.class);
            iOrdenVenta.save(modelMapper.mapear(ordenBuscada,OrdenVentaModel.class));
            response.setResponse(ordenBuscada);
            response.setCode(0);
            response.setMessage("Successful response");
        }
        else{
            response.setCode(2);
            response.setMessage("Bad request");
        }

        return response;
    }
}
