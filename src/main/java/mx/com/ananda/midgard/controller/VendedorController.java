package mx.com.ananda.midgard.controller;

import mx.com.ananda.midgard.response.ListClienteResponse;
import mx.com.ananda.midgard.response.ListOrdenVentaResponse;
import mx.com.ananda.midgard.response.ListVendedorResponse;
import mx.com.ananda.midgard.response.VendedorResponse;
import mx.com.ananda.midgard.service.interfaces.IVendedorService;
import mx.com.ananda.midgard.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ananda/midgard/muspelheim")
public class VendedorController {

    @Autowired
    private IVendedorService sVendedor;

    @GetMapping
    public ResponseEntity<ListVendedorResponse> obtenerVendedores(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idVendedor") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir
    ){
       return new ResponseEntity<>(sVendedor.findAll(pageNumber,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/nombre")
    public ResponseEntity<ListVendedorResponse> obtenerVendedoresByName(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idVendedor") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "nombre") String nombre
    ){
       return new ResponseEntity<>(sVendedor.findAllByName(pageNumber,pageSize,orderBy,sortDir,nombre),HttpStatus.OK);
    }

    @GetMapping("/{idVendedor}")
    public ResponseEntity<VendedorResponse> obtenerVendedorById(@PathVariable(value = "idVendedor") Long idVendedor){
        return new ResponseEntity<>(sVendedor.findById(idVendedor),HttpStatus.OK);
    }

    @GetMapping("/slpCode")
    public ResponseEntity<VendedorResponse> obtenerVendedorBySlpCode(@RequestParam int slpCode){
        return new ResponseEntity<>(sVendedor.findByCodigo(slpCode),HttpStatus.OK);
    }

    @GetMapping("/cliente/{slpCode}")
    public ResponseEntity<ListClienteResponse> obtenerClienteVendedor(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idCliente") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @PathVariable(value = "slpCode") int slpCode
    ){
        return new ResponseEntity<>(sVendedor.findClienteByVendedor(pageNumber,pageSize,orderBy,sortDir,slpCode),HttpStatus.OK);
    }

    @GetMapping("/orden/{slpCode}")
    public ResponseEntity<ListOrdenVentaResponse> obtenerOrdenVendedor(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idOrdenVenta") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @PathVariable(value = "slpCode") int slpCode
    ){
        return new ResponseEntity<>(sVendedor.findOrdenVendedor(pageNumber,pageSize,orderBy,sortDir,slpCode),HttpStatus.OK);
    }

    @GetMapping("/groupCode")
    public ResponseEntity<ListVendedorResponse> obtenerVendedorGroupCode(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idVendedor") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "groupCode") int groupCode
    ){
        return new ResponseEntity<>(sVendedor.findAllByGroupCode(pageNumber,pageSize,orderBy,sortDir,groupCode),HttpStatus.OK);
    }
}
