package mx.com.ananda.midgard.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_orden_request")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrdenVentaRequestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden_request")
    private Long idOrdenVenta;

    @Column(name = "doc_num")
    private Long docNum;

    @Column(name = "doc_total")
    private Double docTotal;

    @Column(name = "metodo_pago")
    private int metodoPago;

    @Column(name = "imagen_orden")
    private String imagen;

    @Column(name = "card_code")
    private String cardCode;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "slp_code")
    private String slpCode;

    @Column(name = "slp_name")
    public String slpName;
}
