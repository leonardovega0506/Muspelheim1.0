package mx.com.ananda.midgard.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

@Configuration
@Lazy
public class GlobalConfig {
    @Bean
    public ModelMapper modelMapper(){return new ModelMapper();}

    @Bean
    public RestTemplate restTemplate(){return new RestTemplate();}
}
