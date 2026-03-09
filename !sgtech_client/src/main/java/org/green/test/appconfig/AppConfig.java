package org.green.test.appconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        // 스프링이 시작될 때 이 객체를 딱 하나 생성해서 가지고 있게 됩니다.
        return new RestTemplate();
    }
}