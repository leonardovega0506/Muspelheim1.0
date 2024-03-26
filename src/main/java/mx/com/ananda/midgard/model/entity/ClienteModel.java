package mx.com.ananda.midgard.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "tbl_cliente")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@JsonIgnoreProperties({"cliente"})
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "card_code")
    private String cardCode;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_type")
    private char cardType;

    @Column(name = "phone1")
    private String phone1;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "group_num")
    private Integer groupNum;

    @Column(name = "credit_line")
    private Double creditLine;

    @Column(name = "debit_line")
    private Double debitLine;

    @Column(name = "lic_trad")
    private String licTradNum;

    @Column(name = "slp_code")
    private Integer slpCode;

    @Column(name = "currency")
    private String currency;

    @Column(name = "balance_sys")
    private Double balanceSys;

    @ManyToOne
    @JoinColumn(name = "vendedor")
    private VendedorModel vendedor;

    @JsonIgnoreProperties({})
    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<OrdenVentaModel> ordenes;

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<OrdenVentaRequestModel> requests;
}

