package mx.com.ananda.midgard.model.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tbl_vendedor")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class VendedorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vendedor")
    private Long idVendedor;

    @Column(name = "slp_code")
    private Integer slpCode;

    @Column(name = "slp_name")
    private String slpName;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "movil")
    private String movil;

    @Column(name = "group_code")
    private Integer groupCode;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "vendedor",cascade = CascadeType.ALL)
    private List<ClienteModel> clientes;

    @OneToMany(mappedBy = "vendedor",cascade = CascadeType.ALL)
    private List<OrdenVentaModel> ordenes;

    @OneToMany(mappedBy = "vendedor",cascade = CascadeType.ALL)
    private List<OrdenVentaRequestModel> requests;

}
