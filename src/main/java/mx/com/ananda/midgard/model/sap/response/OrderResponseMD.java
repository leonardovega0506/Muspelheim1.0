package mx.com.ananda.midgard.model.sap.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.com.ananda.midgard.model.sap.model.OrderSAP;
import mx.com.ananda.midgard.model.sap.model.VendorPaymentSAP;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class OrderResponseMD {

    private int code;
    private String message;
    private OrderSAP response;
    private List<OrderSAP> listResponse;
}
