package com.exercie.api.actionnaire;

import com.exercie.api.actionnaire.adapter.repository.SocieteRepositoryInMemoryTest;

import com.exercie.api.actionnaire.usecase.RecupererBeneficiaireUsecase;
import com.exercie.api.actionnaire.usecase.gateway.SocieteDataAccessGateway;
import org.springframework.context.annotation.Bean;


@org.springframework.boot.test.context.TestConfiguration
public class TestConfiguration {
    @Bean
    public SocieteDataAccessGateway societeDataAccessGateway(){
        return new SocieteRepositoryInMemoryTest();
    }

    @Bean
    public RecupererBeneficiaireUsecase recupererBeneficiaireUsecase(){
        return new RecupererBeneficiaireUsecase(societeDataAccessGateway());
    }


}

