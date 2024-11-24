package com.exercie.api.actionnaire.domain;

public sealed interface BeneficiaireTarget {
    record PersonnePhysique(String nom, float part) implements BeneficiaireTarget { }

    record PersonneMorale(String nom, float part) implements BeneficiaireTarget { }
}
