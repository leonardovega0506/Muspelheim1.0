package mx.com.ananda.midgard.service.interfaces;

import mx.com.ananda.midgard.response.ListOrdenVentaResponse;
import mx.com.ananda.midgard.response.OrdenVentaResponse;

import java.time.LocalDate;

public interface IOrdenVentaService {

    ListOrdenVentaResponse findAll(int numPage, int sizePage, String orderBy, String sortDir);
    ListOrdenVentaResponse findAllDate(int numPage, int sizePage, String orderBy, String sortDir, LocalDate date);
    OrdenVentaResponse findById(Long idOrden);
    OrdenVentaResponse findByDocNum(int docNum);
    OrdenVentaResponse findByFactura(String ref1);
    OrdenVentaResponse updateEstatusOrden(long docNum, int estatus);
}
