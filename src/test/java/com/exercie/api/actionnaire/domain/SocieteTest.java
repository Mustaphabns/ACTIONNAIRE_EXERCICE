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

    List<BeneficiaireTarget> benefciciaresCasTroisieme = List.of(
            new BeneficiaireTarget.PersonnePhysique("Xavier", .3f),
            new BeneficiaireTarget.PersonnePhysique("Zoé", .4f),
            new BeneficiaireTarget.PersonnePhysique("Yvette", .27f),
            new BeneficiaireTarget.PersonnePhysique("Yves", .015000001f),
            new BeneficiaireTarget.PersonneMorale("Societé D", .015000001f),
            new BeneficiaireTarget.PersonneMorale("Societé C", .3f),
            new BeneficiaireTarget.PersonneMorale("Societé B", .6f)

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
    @Test
    void givenSocieteCas3ShouldReturnPersonnePhysiqueEtMorale(){
        //given
        Societe societe = getSocieteCasTroisieme();
        //when
        var benefciciareActuel = societe.getBeneficiaires();
        // then
        assertThat(benefciciareActuel).containsAll(benefciciaresCasTroisieme);
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
                new Action(new Beneficiaire.PersonneMorale("Société B", List.of()), 0.85f)
        );

        return new Societe(1, "Société A", actions);
    }

    static private Societe getSocieteCasTroisieme(){


       Beneficiaire beneficiaireYvette = new Beneficiaire.PersonnePhysique("Yvette");
       Beneficiaire beneficiaireYves = new Beneficiaire.PersonnePhysique("Yves");
       Beneficiaire beneficiaireXavier = new Beneficiaire.PersonnePhysique("Xavier");
       Beneficiaire beneficiaireZoeA = new Beneficiaire.PersonnePhysique("Zoé");
       Beneficiaire beneficiaireZoeB = new Beneficiaire.PersonnePhysique("Zoé");

        Beneficiaire beneficiaireSocieteB = new Beneficiaire.PersonneMorale("Societé B", List.of(
                new Action(beneficiaireZoeB, .5f),
                new Action(new Beneficiaire.PersonneMorale("Societé C", List.of(
                        new Action(beneficiaireYvette, .9f),
                        new Action(beneficiaireYves, .05f),
                        new Action(new Beneficiaire.PersonneMorale("Societé D", List.of()), .05f)
                )), .5f)


        ));


        return new Societe(1, "Société A", List.of(
                new Action(beneficiaireXavier, .3f),
                new Action(beneficiaireZoeA, .1f),
                new Action(beneficiaireSocieteB, .6f)
                ));
    }

}
