package com.exercie.api.actionnaire.usecase;

import com.exercie.api.actionnaire.domain.BeneficiaireTarget;
import com.exercie.api.actionnaire.domain.Societe;
import com.exercie.api.actionnaire.usecase.annotation.DomainService;
import com.exercie.api.actionnaire.usecase.gateway.BeneficiairesDataAccessGateway;
import lombok.AllArgsConstructor;

import java.util.List;

@DomainService
@AllArgsConstructor
public class RecupererBeneficiaireUsecase {

    BeneficiairesDataAccessGateway beneficiairesDataAccessGateway;

    public List<BeneficiaireTarget> beneficiaireTargets(int societeId){
        Societe societe = beneficiairesDataAccessGateway.getSocietes().stream()
                .filter(s -> s.getSocieteId() == societeId)
                .findFirst()
                .orElseThrow();
        return societe.getBeneficiaires();
    }

}
