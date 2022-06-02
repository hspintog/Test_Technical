package co.com.techincalTest.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "co.com.techincalTest",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {


}
