package mx.com.ananda.midgard.model.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "tbl_orden_venta")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrdenVentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private Long idOrdenVenta;

    @Column(name = "doc_entry")
    private Long docEntry;

    @Column(name = "doc_num")
    private Long docNum;

    @Column(name = "obj_type")
    private Integer objType;

    @Column(name = "doc_date")
    private LocalDateTime docDate;

    @Column(name = "doc_due_date")
    private LocalDateTime docDueDate;

    @Column(name = "card_code")
    private String cardCode;

    @Column(name = "slp_code")
    private Integer slpCode;

    @Column(name = "doc_total")
    private Double docTotal;

    @Column(name = "paid_date")
    private Double paidDate;

    @Column(name = "ref1")
    private String ref1;

    @Column(name = "pay_method")
    private Integer peyMethod;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private ClienteModel cliente;

    @ManyToOne
    @JoinColumn
    private VendedorModel vendedor;

    @OneToOne(mappedBy = "orden")
    private OrdenVentaRequestModel request;
}
