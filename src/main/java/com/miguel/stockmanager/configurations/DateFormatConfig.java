package com.miguel.stockmanager.configurations;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Configuration
public class DateFormatConfig {

  @Bean
  @Primary
  Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {

    return builder -> {
      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      builder.deserializers(new LocalDateDeserializer(dateFormatter));
      builder.serializers(new LocalDateSerializer(dateFormatter));
    };
  }
}
