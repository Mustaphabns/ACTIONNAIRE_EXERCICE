package com.exercie.api.actionnaire.adapter.repository;

import com.exercie.api.actionnaire.domain.Action;
import com.exercie.api.actionnaire.domain.Beneficiaire;
import com.exercie.api.actionnaire.domain.Societe;
import com.exercie.api.actionnaire.usecase.gateway.SocieteDataAccessGateway;


import java.util.List;


public class SocieteRepositoryInMemoryTest implements SocieteDataAccessGateway {

    @Override
    public List<Societe> getSocietes() {

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


         Societe societeA = new Societe(1, "Société A", List.of(
                 new Action(beneficiaireXavier, .3f),
                 new Action(beneficiaireZoeA, .1f),
                 new Action(beneficiaireSocieteB, .6f)
         ));

         Societe societeB = new Societe(2, "Société B", List.of(
                 new Action(beneficiaireZoeB, .5f),
                 new Action(new Beneficiaire.PersonneMorale("Societé C", List.of(
                         new Action(beneficiaireYvette, .9f),
                         new Action(beneficiaireYves, .05f),
                         new Action(new Beneficiaire.PersonneMorale("Societé D", List.of()), .05f)
                 )), .5f)
         ));

         Societe societeC = new Societe(4, "Société C", List.of(
                 new Action(beneficiaireYvette, .9f),
                 new Action(beneficiaireYves, .05f),
                 new Action(new Beneficiaire.PersonneMorale("Societé D", List.of()), .05f)
         ));

         Societe societeD = new Societe(3, "Société D", List.of());

         return List.of(societeA, societeB, societeC, societeD);

    }


}