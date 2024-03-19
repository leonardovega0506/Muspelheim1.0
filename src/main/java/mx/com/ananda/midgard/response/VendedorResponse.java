package mx.com.ananda.midgard.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.com.ananda.midgard.model.dto.OrdenVentaDTO;
import mx.com.ananda.midgard.model.dto.VendedorDTO;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VendedorResponse {

    private VendedorDTO response;
    private Integer code;
    private String message;
}
