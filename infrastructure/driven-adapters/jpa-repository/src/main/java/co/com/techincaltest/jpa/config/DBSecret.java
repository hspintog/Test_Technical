package co.com.techincaltest.jpa.config;

import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Builder
@Getter
@ConfigurationProperties(prefix="spring")
public class DBSecret {
    @Value("${spring.datasource.url}")
    private final String url;
    @Value("${spring.datasource.username}")
    private final String username;
    @Value("${spring.datasource.password}")
    private final String password;

}
