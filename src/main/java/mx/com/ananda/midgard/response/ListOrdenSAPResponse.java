package mx.com.ananda.midgard.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.com.ananda.midgard.model.dto.OrdenVentaDTO;
import mx.com.ananda.midgard.model.dto.OrdenVentaRequestDTO;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ListOrdenSAPResponse {

    private List<OrdenVentaRequestDTO> content;
    private Integer code;
    private String message;
    private Integer numPage;
    private Integer sizePage;
    private Long totalElements;
    private Integer totalPages;
    private Boolean isLast;
}
