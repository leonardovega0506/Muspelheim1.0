package mx.com.ananda.midgard.model.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class OrdenVentaDTO {

    private Long idOrdenVenta;
    private Long docEntry;
    private Long docNum;
    private Integer objType;
    private LocalDateTime docDate;
    private LocalDateTime docDueDate;
    private String cardCode;
    private Integer slpCode;
    private Double docTotal;
    private Double paidDate;
    private String ref1;
    private Integer peyMethod;
}
