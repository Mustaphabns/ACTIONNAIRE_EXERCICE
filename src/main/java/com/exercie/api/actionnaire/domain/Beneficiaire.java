package com.exercie.api.actionnaire.domain;

public sealed interface Beneficiaire {

    String getNom();
    record PersonnePhysique(String nom) implements Beneficiaire {
        @Override
        public String getNom() {
            return nom;
        }
    }

    record PersonneMorale(String nom) implements Beneficiaire {
        @Override
        public String getNom() {
            return nom;
        }
    }
}
