package com.exercie.api.actionnaire.adapter.repository;

import com.exercie.api.actionnaire.domain.Societe;
import com.exercie.api.actionnaire.usecase.gateway.BeneficiairesDataAccessGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class BeneficiaireRepositoryInMemory implements BeneficiairesDataAccessGateway {
    private final List<Societe> societes;

    @Override
    public List<Societe> getSocietes() {
        return this.societes;
    }
}
