package mx.com.ananda.midgard.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

}
