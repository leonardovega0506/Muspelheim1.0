package mx.com.ananda.midgard.model.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class OrdenVentaRequestDTO {

    private Long idOrdenVenta;
    private Long docNum;
    private Double docTotal;
    private int metodoPago;
    private String imagen;
    private String cardCode;
    private String cardName;
    private String slpCode;
    public String slpName;
}
