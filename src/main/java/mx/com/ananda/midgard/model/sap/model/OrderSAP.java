package mx.com.ananda.midgard.model.sap.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrderSAP {

    private int DocEntry;
    private int DocNum;
    private String ObjType;
    private LocalDate DocDate;
    private LocalDate DocDueDate;
    private String CardCode;
    private int SlpCode;
    private Double DocTotal;
    private Double PaidToDate;
    private String  Ref1;
    private String PeyMethod;
    private String U_EstatusOV;
}
