package com.exercie.api.actionnaire.usecase;

import com.exercie.api.actionnaire.TestConfiguration;
import com.exercie.api.actionnaire.domain.BeneficiaireTarget;
import com.exercie.api.actionnaire.domain.exceptions.AucunBeneficiaireTrouverException;
import com.exercie.api.actionnaire.domain.exceptions.EntrepriseNonTrouverException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringJUnitConfig(TestConfiguration.class)
class RecupererBeneficiaireUsecaseTest {

    @Autowired
    private RecupererBeneficiaireUsecase recupererBeneficiaireUsecase;

    @Autowired
    private RecupererBeneficiaireUsecase recupererBeneficiaireUsecase1;
    private static List<BeneficiaireTarget> benefciciares1 = List.of(
            new BeneficiaireTarget.PersonnePhysique("Xavier", .3f),
            new BeneficiaireTarget.PersonnePhysique("Zoé", .4f),
            new BeneficiaireTarget.PersonnePhysique("Yvette", .27f),
            new BeneficiaireTarget.PersonnePhysique("Yves", .015000001f),
            new BeneficiaireTarget.PersonneMorale("Societé D", .015000001f),
            new BeneficiaireTarget.PersonneMorale("Societé C", .3f),
            new BeneficiaireTarget.PersonneMorale("Societé B", .6f)

    );

    private static List<BeneficiaireTarget> benefciciaresAll = List.of(
            new BeneficiaireTarget.PersonnePhysique("Xavier", .3f),
            new BeneficiaireTarget.PersonnePhysique("Zoé", .4f),
            new BeneficiaireTarget.PersonnePhysique("Yvette", .27f),
            new BeneficiaireTarget.PersonnePhysique("Yves", .015000001f),
            new BeneficiaireTarget.PersonneMorale("Societé D", .015000001f),
            new BeneficiaireTarget.PersonneMorale("Societé C", .3f),
            new BeneficiaireTarget.PersonneMorale("Societé B", .6f)

    );

    private static List<BeneficiaireTarget> benefciciaresBeneficiaireEffectifs = List.of(
            new BeneficiaireTarget.PersonnePhysique("Xavier", .3f),
            new BeneficiaireTarget.PersonnePhysique("Zoé", .4f),
            new BeneficiaireTarget.PersonnePhysique("Yvette", .27f)
    );

    private static List<BeneficiaireTarget> benefciciaresPersonnePhysique = List.of(
            new BeneficiaireTarget.PersonnePhysique("Xavier", .3f),
            new BeneficiaireTarget.PersonnePhysique("Zoé", .4f),
            new BeneficiaireTarget.PersonnePhysique("Yvette", .27f),
            new BeneficiaireTarget.PersonnePhysique("Yves", .015000001f)

    );

    @ParameterizedTest
    @MethodSource("beneficiaireUserCase")
    void beneficiaireUserCaseTest(int id, Scope scope, List<BeneficiaireTarget> beneficiaireTargets ){
        Assertions.assertThat(beneficiaireTargets).containsAll(recupererBeneficiaireUsecase.getBeneficiaires(id,scope));
    }

    private static Stream<Arguments> beneficiaireUserCase() {
        return Stream.of(
                Arguments.of(1, Scope.ALL, benefciciares1),
                Arguments.of(1, Scope.PERSONNE_PHYSIQUE, benefciciaresPersonnePhysique),
                Arguments.of(1, Scope.BENEFICIAIRE_EFFECTIFS, benefciciaresBeneficiaireEffectifs)
        );
    }

    @Test
    void beneficiaireUserCaseException(){
        //given
        List<BeneficiaireTarget> beneficiaireTargets = Collections.emptyList();
        // when then
        assertThrows(EntrepriseNonTrouverException.class, () -> recupererBeneficiaireUsecase.getBeneficiaires(5, Scope.ALL));
        assertThrows(AucunBeneficiaireTrouverException.class, () -> recupererBeneficiaireUsecase1.getBeneficiaires(3, Scope.ALL));

    }

}