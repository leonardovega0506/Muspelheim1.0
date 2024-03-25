package mx.com.ananda.midgard.model.dto;

import javax.persistence.Column;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class OrdenVentaRequestDTO {

    private Long idOrdenVenta;
    private Long docNum;
    private Double docTotal;
    private int metodoPago;
    private LocalDate docDate;
    private LocalTime docTime;
    private String imagen;
    private String estatusOrden;
    private String cardCode;
    private String cardName;
    private String slpCode;
    public String slpName;
}
