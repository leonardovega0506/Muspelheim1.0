package mx.com.ananda.midgard.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.com.ananda.midgard.model.dto.ClienteDTO;
import mx.com.ananda.midgard.model.dto.OrdenVentaDTO;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrdenVentaResponse {

    private OrdenVentaDTO response;
    private Integer code;
    private String message;
}
