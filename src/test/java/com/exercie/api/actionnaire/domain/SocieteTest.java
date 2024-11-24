package com.exercie.api.actionnaire.domain;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;


public class SocieteTest {

    List<BeneficiaireTarget> benefciciaresCasPremier = List.of(
            new BeneficiaireTarget.PersonnePhysique("Yves", 0.2f),
            new BeneficiaireTarget.PersonnePhysique("Xavier", 0.5f),
            new BeneficiaireTarget.PersonnePhysique("Zoé", 0.3f)
    );
    List<BeneficiaireTarget> benefciciaresCasDeuxieme = List.of(
            new BeneficiaireTarget.PersonnePhysique("Yves", 0.6f),
            new BeneficiaireTarget.PersonnePhysique("Zoé", 0.15f),
            new BeneficiaireTarget.PersonneMorale("Société B", 0.85f)
    );


    @Test
    void givenSocieteCas1ShouldReturnPersonnePhysique(){
        //given
        Societe societe = getSocieteCasPremier();
        //when
        var benefciciareActuel = societe.getBeneficiaires();
        // then
        assertThat(benefciciareActuel).containsAll(benefciciaresCasPremier);
    }

    @Test
    void givenSocieteCas2ShouldReturnPersonnePhysiqueEtMorale(){
        //given
        Societe societe = getSocieteCasDeuxieme();
        //when
        var benefciciareActuel = societe.getBeneficiaires();
        // then
        assertThat(benefciciareActuel).containsAll(benefciciaresCasDeuxieme);
    }


    static private Societe getSocieteCasPremier(){

         List<Action>  actions = List.of(
                new Action(new Beneficiaire.PersonnePhysique("Yves"), .2f),
                new Action(new Beneficiaire.PersonnePhysique("Xavier"), 0.5f),
                new Action(new Beneficiaire.PersonnePhysique("Zoé"), 0.3f)
        );

        return new Societe(1, "Société A", actions);
    }


    static private Societe getSocieteCasDeuxieme(){

        List<Action>  actions = List.of(
                new Action(new Beneficiaire.PersonnePhysique("Yves"), .6f),
                new Action(new Beneficiaire.PersonnePhysique("Zoé"), 0.15f),
                new Action(new Beneficiaire.PersonneMorale("Société B"), 0.85f)
        );

        return new Societe(1, "Société A", actions);
    }
}
