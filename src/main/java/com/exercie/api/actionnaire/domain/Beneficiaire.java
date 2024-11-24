package com.exercie.api.actionnaire.domain;

import java.util.List;

public sealed interface Beneficiaire {

    record PersonnePhysique(String nom) implements Beneficiaire { }

    record PersonneMorale(String nom, List<Action> actions) implements Beneficiaire { }
}
