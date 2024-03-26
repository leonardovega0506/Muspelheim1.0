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
public class ClienteController{

    @Autowired
    private IClienteService sCliente;

    @GetMapping
    public ResponseEntity<ListClienteResponse> obtenerClientes(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idCliente") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir
    ){
        ListClienteResponse response = sCliente.findAll(pageNumber,pageSize,orderBy,sortDir);
        if(response.getCode()==0){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else if(response.getCode()==1){
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/cardName")
    public ResponseEntity<ListClienteResponse> obtenerClientesByCardName(
            @RequestParam String cardName
    ){
        ListClienteResponse response = sCliente.findAllByName(cardName);
        if(response.getCode()==0){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else if(response.getCode()==1){
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        else if(response.getCode()==2){
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cardCode")
    public ResponseEntity<ClienteResponse> obtenerClienteByCardCode(@RequestParam(value = "cardCode") String cardCode){

        ClienteResponse response = sCliente.findClienteByCardCode(cardCode);

        if(response.getCode()==0){

            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else if(response.getCode()==1){
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        else if(response.getCode()==2){
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/id/{idCliente}")
    public ResponseEntity<ClienteResponse> obtenerClienteById(@PathVariable(value = "idCliente") Long idCliente){

        ClienteResponse response = sCliente.findClienteById(idCliente);

        if(response.getCode()==0){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else if(response.getCode() == 1){
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/orden-venta/{idCliente}")
    public ResponseEntity<ListOrdenVentaResponse> obtenerVentasClientes(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idOrdenVenta") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @PathVariable(value = "idCliente") Long idCliente
    ){
        ListOrdenVentaResponse response = sCliente.findOrdenVentaCliente(pageNumber,pageSize,orderBy,sortDir,idCliente);

        if(response.getCode()==0){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else if(response.getCode()==1){
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        else if(response.getCode() == 2){
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
