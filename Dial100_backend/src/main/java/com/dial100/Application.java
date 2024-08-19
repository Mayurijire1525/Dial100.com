package com.dial100;

import org.hibernate.collection.internal.PersistentBag;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ModelMapper mapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(Conditions.isNotNull());

        // Add custom converter for PersistentBag to List
        modelMapper.addConverter(new Converter<PersistentBag, List<?>>() {
            @Override
            public List<?> convert(MappingContext<PersistentBag, List<?>> context) {
                return new ArrayList<>(context.getSource());
            }
        });

        return modelMapper;
    }

}
