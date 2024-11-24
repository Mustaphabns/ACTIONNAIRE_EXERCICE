package com.exercie.api.actionnaire.domain;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;


public class SocieteTest {

    List<BeneficiaireTarget> benefciciaresCasPremier = List.of(
            new BeneficiaireTarget("Yves", 0.2f),
            new BeneficiaireTarget("Xavier", 0.5f),
            new BeneficiaireTarget("Zoé", 0.3f)
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


    static private Societe getSocieteCasPremier(){

         List<Action>  actions = List.of(
                new Action(new Beneficiaire.PersonnePhysique("Yves"), .2f),
                new Action(new Beneficiaire.PersonnePhysique("Xavier"), 0.5f),
                new Action(new Beneficiaire.PersonnePhysique("Zoé"), 0.3f)
        );

        return new Societe(1, "Société A", actions);
    }
}
