package com.rasto.stockscreener.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Currency;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter((Converter<Currency, String>) context -> context.getSource() == null ? null : context.getSource().toString());
        modelMapper.addConverter(context -> context.getSource() == null ? null : Currency.getInstance(context.getSource()), String.class, Currency.class);
        return modelMapper;
    }
}
