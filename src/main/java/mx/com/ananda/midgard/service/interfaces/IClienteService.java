package mx.com.ananda.midgard.service.interfaces;

import mx.com.ananda.midgard.response.ClienteResponse;
import mx.com.ananda.midgard.response.ListClienteResponse;
import mx.com.ananda.midgard.response.ListOrdenVentaResponse;

public interface IClienteService {

    ListClienteResponse findAll(int numPage,int sizePage, String orderBy, String sortDir);
    ListClienteResponse findAllByName(String cardName);
    ClienteResponse findClienteByCardCode(String cardCode);

    ClienteResponse findClienteById(Long id);
    ListOrdenVentaResponse findOrdenVentaCliente(int numPage, int sizePage, String orderBy, String sortDir, Long idCliente);
}
