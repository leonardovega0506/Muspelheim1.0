package mx.com.ananda.midgard.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.com.ananda.midgard.model.entity.OrdenVentaModel;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonIgnoreProperties({"cliente"})
public class ClienteDTO {

    private Long idCliente;
    private String cardCode;
    private String cardName;
    private char cardType;
    private String phone1;
    private Double balance;
    private Integer groupNum;
    private Double creditLine;
    private Double debitLine;
    private String licTradNum;
    private Integer slpCode;
    private String currency;
    private Double balanceSys;
    private List<OrdenVentaModel> ordenes;
}
