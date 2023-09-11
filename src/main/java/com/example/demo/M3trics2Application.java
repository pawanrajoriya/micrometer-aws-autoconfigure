package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.export.simple.SimpleMetricsExportAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.aws.autoconfigure.context.ContextCredentialsAutoConfiguration;
import org.springframework.cloud.aws.core.region.RegionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import io.micrometer.cloudwatch2.CloudWatchMeterRegistry;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@Configuration(proxyBeanMethods = false)
@Import(ContextCredentialsAutoConfiguration.class)
@AutoConfigureBefore({ CompositeMeterRegistryAutoConfiguration.class, SimpleMetricsExportAutoConfiguration.class })
@AutoConfigureAfter(MetricsAutoConfiguration.class)
@EnableConfigurationProperties(CloudWatchProperties1.class)
@ConditionalOnProperty(prefix = "management.metrics.export.cloudwatch", name = "namespace")
@ConditionalOnClass({ CloudWatchMeterRegistry.class, RegionProvider.class })
public class M3trics2Application {
  
  @Autowired
  Environment env;

  @Bean
  RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.messageConverters(new StringHttpMessageConverter(), new MappingJackson2HttpMessageConverter()).build();
  }
  
//  @Bean
//  public CloudWatchMeterRegistry cloudWatchMeterRegistry(CloudWatchConfig config, Clock clock, CloudWatchAsyncClient client) {
//      return new CloudWatchMeterRegistry(config, clock, client);
//  }
//  
//  @Bean
//  public Clock micrometerClock() {
//      return Clock.SYSTEM;
//  }
//  
//  @Bean
//  public CloudWatchAsyncClient  amazonCloudWatchAsync() {
//      return CloudWatchAsyncClient.create();
//  }
//  
// 
//  @Bean
//  public CloudWatchConfig cloudWatchConfig() {
//      return new CloudWatchConfig() {
//          @Override
//          public String prefix() {
//              return null;
//          }
//
//          @Override
//          public String namespace() {
//              return env.getProperty("management.metrics.export.cloudwatch.namespace");              
//          }
//
//          @Override
//          public Duration step() {
//              return properties.getStep();
//          }
//
//          @Override
//          public boolean enabled() {
//              return env.getProperty("management.metrics.export.cloudwatch.enabled",Boolean.class);
//          }
//
//          @Override
//          public int batchSize() {
//              return env.getProperty("management.metrics.export.cloudwatch.batchSize",Integer.class);
//          }
//
//          @Override
//          public String get(String s) {
//              return null;
//          }
//      };
//
//  }

  public static void main(String[] args) {
    SpringApplication.run(M3trics2Application.class, args);
  }
}
