package mx.com.ananda.midgard.service.implementation;

import mx.com.ananda.midgard.model.dto.OrdenVentaDTO;
import mx.com.ananda.midgard.model.dto.OrdenVentaRequestDTO;
import mx.com.ananda.midgard.model.entity.OrdenVentaModel;
import mx.com.ananda.midgard.model.entity.OrdenVentaRequestModel;
import mx.com.ananda.midgard.repositories.IOrdenVentaRequestRepository;
import mx.com.ananda.midgard.response.ListOrdenSAPResponse;
import mx.com.ananda.midgard.service.interfaces.IOrdenVentaRequestService;
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
public class OrdenVentaRequestServiceImpl implements IOrdenVentaRequestService {

    @Autowired
    private IOrdenVentaRequestRepository iOrdenVenta;

    @Autowired
    private MapperServiceImpl modelMapper;

    @Override
    public void LiberarOrden(Long id) {
       OrdenVentaRequestModel ordenBuscada= iOrdenVenta.findById(id).orElseThrow();
       ordenBuscada.setEstatusOrden("Liberado");
       iOrdenVenta.save(ordenBuscada);
    }

    @Override
    public void uploadOrden(OrdenVentaRequestDTO request) {
        request.setEstatusOrden("Cargado");
        iOrdenVenta.save(modelMapper.mapear(request,OrdenVentaRequestModel.class));
    }

    @Override
    public ListOrdenSAPResponse findAll(int numPage, int sizePage, String orderBy, String sortDir) {
        ListOrdenSAPResponse response = new ListOrdenSAPResponse();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, sizePage, sort);
        Page<OrdenVentaRequestModel> ordenes = iOrdenVenta.findAll(pageable);
        List<OrdenVentaRequestModel> listaOrdenes = ordenes.getContent();
        List<OrdenVentaRequestDTO> contenido = listaOrdenes.stream().map(orden -> modelMapper.mapear(orden, OrdenVentaRequestDTO.class)).collect(Collectors.toList());

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
    public ListOrdenSAPResponse findAllDate(int numPage, int sizePage, String orderBy, String sortDir, LocalDate fecha) {
        ListOrdenSAPResponse response = new ListOrdenSAPResponse();
        if(fecha!=null) {
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
            Pageable pageable = PageRequest.of(numPage, sizePage, sort);
            Page<OrdenVentaRequestModel> ordenes = iOrdenVenta.findByDocDate(fecha,pageable);
            List<OrdenVentaRequestModel> listaOrdenes = ordenes.getContent();
            List<OrdenVentaRequestDTO> contenido = listaOrdenes.stream().map(orden -> modelMapper.mapear(orden, OrdenVentaRequestDTO.class)).collect(Collectors.toList());

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
}
