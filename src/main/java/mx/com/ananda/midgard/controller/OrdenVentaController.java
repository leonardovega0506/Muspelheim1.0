package mx.com.ananda.midgard.controller;

import mx.com.ananda.midgard.model.dto.OrdenVentaRequestDTO;
import mx.com.ananda.midgard.response.ListOrdenSAPResponse;
import mx.com.ananda.midgard.response.ListOrdenVentaResponse;
import mx.com.ananda.midgard.response.OrdenVentaResponse;
import mx.com.ananda.midgard.service.interfaces.IOrdenVentaRequestService;
import mx.com.ananda.midgard.service.interfaces.IOrdenVentaService;
import mx.com.ananda.midgard.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/ananda/midgard/muspelheim/orden")
public class OrdenVentaController {

    @Autowired
    private IOrdenVentaService sOrden;

    @Autowired
    private IOrdenVentaRequestService sRequest;

    @GetMapping
    public ResponseEntity<ListOrdenVentaResponse> obtenerOrdenes(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idCliente") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir
    ){
        return new ResponseEntity<>(sOrden.findAll(pageNumber,pageSize,orderBy,sortDir),HttpStatus.OK);
    }

    @GetMapping("/fecha")
    public ResponseEntity<ListOrdenVentaResponse> obtenerOrdenesFecha(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idCliente") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "fecha")LocalDate fecha
    ){
        return new ResponseEntity<>(sOrden.findAllDate(pageNumber,pageSize,orderBy,sortDir,fecha),HttpStatus.OK);
    }

    @GetMapping("/sap")
    public ResponseEntity<ListOrdenSAPResponse> obtenerOrdenesSAP(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idOrdenVenta") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir
    ){
        return new ResponseEntity<>(sRequest.findAll(pageNumber,pageSize,orderBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/sap/fecha")
    public ResponseEntity<ListOrdenSAPResponse> obtenerOrdenesFechaSAP(
            @RequestParam(value = "pageNo", defaultValue = GlobalConstants.NUMERO_PAGINA_DEFECTO) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = GlobalConstants.MEDIDA_PAGINA_DEFECTO) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "idCliente") String orderBy,
            @RequestParam(value = "sortDir", defaultValue = GlobalConstants.ORDENAR_DIRECCION_DEFECTO) String sortDir,
            @RequestParam(value = "fecha")LocalDate fecha
            ){
        return new ResponseEntity<>(sRequest.findAllDate(pageNumber,pageSize,orderBy,sortDir,fecha),HttpStatus.OK);
    }

    @GetMapping("/{idOrdenVenta}")
    public ResponseEntity<OrdenVentaResponse> obtenerOrdenById(@PathVariable (value = "idOrdenVenta") Long idOrdenVenta){
        return new ResponseEntity<>(sOrden.findById(idOrdenVenta),HttpStatus.OK);
    }

    @GetMapping("/docNum")
    public ResponseEntity<OrdenVentaResponse> obtenerOrdenByDocNum(@RequestParam Integer docNum){
        return new ResponseEntity<>(sOrden.findByDocNum(docNum),HttpStatus.OK);
    }

    @PostMapping("/liberar-orden")
    public ResponseEntity<String> LiberarOrden(@RequestParam OrdenVentaRequestDTO request){
        sRequest.LiberarOrden(request);
        return new ResponseEntity<>("Liberado",HttpStatus.OK);
    }

    @PutMapping("/estatus")
    public ResponseEntity<OrdenVentaResponse> actualizarOrden(@RequestParam long docNum, int estatus){
        return new ResponseEntity<>(sOrden.updateEstatusOrden(docNum,estatus),HttpStatus.OK);
    }
}
