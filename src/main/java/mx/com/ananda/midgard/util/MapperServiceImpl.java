package mx.com.ananda.midgard.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MapperServiceImpl {

    @Autowired
    private final ModelMapper modelMapper;

    public  <S, T> T mapear(S source, Class<T> target) {
        return modelMapper.map(source, target);
    }
}
