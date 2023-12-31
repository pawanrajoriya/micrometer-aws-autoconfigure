package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class M3trics2Application {

  @Bean
  RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.messageConverters(new StringHttpMessageConverter(), new MappingJackson2HttpMessageConverter()).build();
  }

  public static void main(String[] args) {
    SpringApplication.run(M3trics2Application.class, args);
  }
}
