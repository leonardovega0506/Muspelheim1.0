package mx.com.ananda.midgard.model.sap.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VendorPaymentSAP {

    private String CardCode;
    private String CardName;
    private char CardType;
    private String Phone1;
    private Double Balance;
    private int GroupNum;
    private Double CreditLine;
    private Double DebitLine;
    private String LicTradNum;
    private int SlpCode;
    private String Currency;
    private Double BalanceSys;
}
