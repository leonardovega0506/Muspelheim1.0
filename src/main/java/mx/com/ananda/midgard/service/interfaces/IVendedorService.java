package mx.com.ananda.midgard.service.interfaces;

import mx.com.ananda.midgard.response.ListClienteResponse;
import mx.com.ananda.midgard.response.ListOrdenVentaResponse;
import mx.com.ananda.midgard.response.ListVendedorResponse;
import mx.com.ananda.midgard.response.VendedorResponse;

public interface IVendedorService {

    ListVendedorResponse findAll(int numPage, int sizePage, String orderBy, String sortDir);
    ListVendedorResponse findAllByName(int numPage, int sizePage, String orderBy, String sortDir,String name);
    VendedorResponse findById(Long idVendedor);
    VendedorResponse findByCodigo(int slpCode);
    ListClienteResponse findClienteByVendedor(int numPage, int sizePage, String orderBy, String sortDir,int slpCode);
    ListOrdenVentaResponse findOrdenVendedor(int numPage, int sizePage, String orderBy, String sortDir,int slpCode);
    ListVendedorResponse findAllByGroupCode(int numPage, int sizePage, String orderBy, String sortDir,int groupCode);
}
