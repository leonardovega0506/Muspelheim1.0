package mx.com.ananda.midgard.model.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ClienteDTO {

    private Long idCliente;
    private Integer cardCode;
    private String cardName;
    private char cardType;
    private String phone1;
    private Double balance;
    private Integer groupNum;
    private Double creditLine;
    private Double debitLine;
    private String lidTadNum;
    private Integer slpCode;
    private String currency;
    private Double balanceSys;
}
