package mx.com.ananda.midgard.controller;

import mx.com.ananda.midgard.response.ClienteResponse;
import mx.com.ananda.midgard.response.ListClienteResponse;
import mx.com.ananda.midgard.response.ListOrdenVentaResponse;
import mx.com.ananda.midgard.service.interfaces.IClienteService;
import mx.com.ananda.midgard.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ananda/midgard/muspelheim/cliente")
public class ClienteController {

    @Autowired
    private IClienteService sCliente;

    @GetMapping
    public ResponseEntity<ListClienteResponse> obtenerClientes(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idCliente") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir
    ){
        return new ResponseEntity<>(sCliente.findAll(pageNumber,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/cardName")
    public ResponseEntity<ListClienteResponse> obtenerClientesByCardName(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idCliente") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "cardName") String cardName
    ){
        return new ResponseEntity<>(sCliente.findAllByName(pageNumber,pageSize,orderBy,sortDir,cardName),HttpStatus.OK);
    }

    @GetMapping("/cardCode/{cardCode}")
    public ResponseEntity<ClienteResponse> obtenerClienteByCardCode(@PathVariable(value = "cardCode") Integer cardCode){
        return new ResponseEntity<>(sCliente.findClienteByCardCode(cardCode),HttpStatus.OK);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteResponse> obtenerClienteById(@PathVariable(value = "idCliente") Long idCliente){
        return new ResponseEntity<>(sCliente.findClienteById(idCliente),HttpStatus.OK);
    }

    @GetMapping("/orden-venta/{idCliente}")
    public ResponseEntity<ListOrdenVentaResponse> obtenerVentasClientes(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idOrdenVenta") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @PathVariable(value = "idCliente") Long idCliente
    ){
        return new ResponseEntity<>(sCliente.findOrdenVentaCliente(pageNumber,pageSize,orderBy,sortDir,idCliente),HttpStatus.OK);
    }

}
