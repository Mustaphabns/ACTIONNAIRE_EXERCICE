package com.exercie.api.actionnaire.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
            case Beneficiaire.PersonneMorale m -> {
                benificiaires.add(new BeneficiaireTarget.PersonneMorale(m.nom(), action.part()));
                next(m, action.part(), benificiaires);
            }
        }

        return aditionneParts(benificiaires);
    }

    private void next(Beneficiaire.PersonneMorale beneficiaire, float part, List<BeneficiaireTarget> benificiares){
        for(var action : beneficiaire.actions()){
            switch (action.beneficiaire()) {
                case Beneficiaire.PersonnePhysique p-> benificiares
                        .add(new BeneficiaireTarget.PersonnePhysique(p.nom(), part * action.part()));
                case Beneficiaire.PersonneMorale m -> {
                    benificiares.add(new BeneficiaireTarget.PersonneMorale(m.nom(),part * action.part()));
                    next(m, part * action.part(), benificiares);
                }
            }
        }
    }

    private List<BeneficiaireTarget> aditionneParts(List<BeneficiaireTarget> beneficiares){

        return beneficiares.stream()
                .collect(Collectors
                        .groupingBy(Function.identity(), Collectors.summingDouble(BeneficiaireTarget::getPart)))
                .entrySet().stream()
                .map(entry -> (BeneficiaireTarget) switch (entry.getKey()) {
                    case BeneficiaireTarget.PersonnePhysique p -> new BeneficiaireTarget.PersonnePhysique(p.nom(), entry.getValue().floatValue());
                    case BeneficiaireTarget.PersonneMorale m ->  new BeneficiaireTarget.PersonneMorale(m.nom(), entry.getValue().floatValue());
                }).toList();

    }

}
