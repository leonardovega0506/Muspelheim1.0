package mx.com.ananda.midgard.model.dto;

import javax.persistence.Column;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VendedorDTO {

    private Long idVendedor;
    private Integer slpCode;
    private String slpName;
    private String telephone;
    private String movil;
    private Integer groupCode;
    private String email;
}
