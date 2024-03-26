package mx.com.ananda.midgard.model.sap.response;

import lombok.Data;
import mx.com.ananda.midgard.model.sap.model.VendorPaymentSAP;

import java.util.List;

@Data
public class VendorPaymentResponseMD {

    private int code;
    private String message;
    private VendorPaymentSAP response;
    private List<VendorPaymentSAP> listResponse;
}
