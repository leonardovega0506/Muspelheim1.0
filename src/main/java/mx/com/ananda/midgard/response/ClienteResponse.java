package mx.com.ananda.midgard.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.com.ananda.midgard.model.dto.ClienteDTO;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class ClienteResponse {
    private ClienteDTO response;
    private Integer code;
    private String message;
}
