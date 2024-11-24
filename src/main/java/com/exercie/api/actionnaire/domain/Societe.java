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

    public List<Action> getActions() {
        return actions != null ? Collections.unmodifiableList(actions) : List.of();
    }

    public List<BeneficiaireTarget> getBeneficiaires(){
        List<BeneficiaireTarget> beneficiaires = new ArrayList<>();
        for (var action : getActions()){
            getSousBeneficiares(action, beneficiaires);;
        }
        return Collections.unmodifiableList(beneficiaires);
    }
    private List<BeneficiaireTarget> getSousBeneficiares(Action action, List<BeneficiaireTarget> benificiaires){

        switch (action.beneficiaire()) {
            case Beneficiaire.PersonnePhysique p->  benificiaires
                    .add(new BeneficiaireTarget.PersonnePhysique(p.nom(), action.part()));
            case Beneficiaire.PersonneMorale m -> benificiaires.add(new BeneficiaireTarget.PersonneMorale(m.nom(), action.part()));
        }

        return benificiaires;
    }

}
