package com.exercie.api.actionnaire.domain;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;


public class SocieteTest {

    private static List<BeneficiaireTarget>  benefciciares = List.of(
            new BeneficiaireTarget("Yves", 0.2f),
            new BeneficiaireTarget("Xavier", 0.5f),
            new BeneficiaireTarget("Zoé", 0.3f)
    );

    private static List<Action>  actions = List.of(
            new Action(new PersonnePhysique("Yves"), .2f),
            new Action(new PersonnePhysique("Xavier"), 0.5f),
            new Action(new PersonnePhysique("Zoé"), 0.3f)
    );

    private static Societe societeA = new Societe(1, "Société A", actions);


    @Test
    void givenSocieteShouldReturnPersonnePhysique(){
        //given
        Societe societe = societeA;
        //when
        var benefciciareActuel = societe.getBeneficiaires();
        // then
        assertThat(benefciciareActuel).containsAll(benefciciares);
    }
}
