package com.example.voyages_api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    //    TODO update model mapper for nested object
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
