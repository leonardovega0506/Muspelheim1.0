package mx.com.ananda.midgard.service.interfaces;

import mx.com.ananda.midgard.model.dto.OrdenVentaDTO;
import mx.com.ananda.midgard.model.dto.OrdenVentaRequestDTO;
import mx.com.ananda.midgard.response.ListOrdenSAPResponse;

import java.time.LocalDate;

public interface IOrdenVentaRequestService {

    void LiberarOrden(Long id);
    void uploadOrden(OrdenVentaRequestDTO request);
    ListOrdenSAPResponse findAll(int numPage, int sizePage, String orderBy, String sortDir);
    ListOrdenSAPResponse findAllDate(int numPage, int sizePage, String orderBy, String sortDir, LocalDate fecha);
}
