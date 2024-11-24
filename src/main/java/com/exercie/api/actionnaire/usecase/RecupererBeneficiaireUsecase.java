package com.exercie.api.actionnaire.usecase;

import com.exercie.api.actionnaire.domain.BeneficiaireTarget;
import com.exercie.api.actionnaire.domain.Societe;
import com.exercie.api.actionnaire.usecase.annotation.DomainService;
import com.exercie.api.actionnaire.usecase.gateway.SocieteDataAccessGateway;
import lombok.AllArgsConstructor;

import java.util.List;

@DomainService
@AllArgsConstructor
public class RecupererBeneficiaireUsecase {

    SocieteDataAccessGateway societeDataAccessGateway;

    public List<BeneficiaireTarget> getBeneficiaires(int societeId, Scope scope){

        Societe societe = societeDataAccessGateway.getSocietes().stream()
                .filter(s -> s.getSocieteId() == societeId)
                .findFirst()
                .orElseThrow();
        var beneficiaire =  switch (scope){
            case ALL -> societe.getBeneficiaires();
            case PERSONNE_PHYSIQUE -> societe.getBeneficiaires().stream()
                    .filter(beneficiaireTarget -> beneficiaireTarget instanceof BeneficiaireTarget.PersonnePhysique)
                    .toList();
            case BENEFICIAIRE_EFFECTIFS -> societe.getBeneficiaires().stream()
                    .filter(beneficiaireTarget ->
                            beneficiaireTarget instanceof BeneficiaireTarget.PersonnePhysique p && p.estBeneficiaireEffectif())
                    .toList();
        };

        return beneficiaire;

    }

}
