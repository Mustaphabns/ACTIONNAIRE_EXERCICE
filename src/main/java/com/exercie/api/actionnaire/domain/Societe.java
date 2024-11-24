package com.exercie.api.actionnaire.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Societe {
    private int societeId;
    private String name;
    private List<Action> actions;

    public Societe(int societeId, String name, List<Action> actions) {
        this.societeId = societeId;
        this.name = name;
        this.actions = actions;
    }

    public List<BeneficiaireTarget> getBeneficiaires(){
        List<BeneficiaireTarget> beneficiaires = new ArrayList<>();
        for (var action : actions){
            beneficiaires.add(new BeneficiaireTarget(action.personnePhysique().nom(), action.part()));
        }
        return Collections.unmodifiableList(beneficiaires);
    }

}
