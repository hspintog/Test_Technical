package co.com.techincalTest.config;

import co.com.techincalTest.model.gateways.MutantRepository;
import co.com.techincaltest.jpa.IMutantRepository;
import co.com.techincaltest.jpa.JpaMutantImpl;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }

    @Bean
    @Primary
    public MutantRepository mutantRepository(IMutantRepository repository){
        return new JpaMutantImpl(repository);
    }


}
