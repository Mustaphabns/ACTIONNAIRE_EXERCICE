package com.exercie.api.actionnaire.domain;

import java.util.Objects;

public sealed interface BeneficiaireTarget {
    float getPart();
    record PersonnePhysique(String nom, float part) implements BeneficiaireTarget {
        @Override
        public float getPart() {
            return part;
        }

        public boolean estBeneficiaireEffectif(){
            return part >= .25f;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PersonnePhysique that)) return false;
            return Objects.equals(nom, that.nom);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nom);
        }
    }

    record PersonneMorale(String nom, float part) implements BeneficiaireTarget {
        @Override
        public float getPart() {
            return part;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PersonneMorale that)) return false;
            return Objects.equals(nom, that.nom);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nom);
        }
    }
}
