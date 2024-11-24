package com.exercie.api.actionnaire.adapter.representation;

import com.exercie.api.actionnaire.domain.BeneficiaireTarget;

import java.text.NumberFormat;
import java.util.List;

public record BeneficiaireRepresentation(String nom, String part) {

    public static List<BeneficiaireRepresentation> fromDomain(List<BeneficiaireTarget> beneficiaireDomain){
        return beneficiaireDomain.stream()
                .map(p -> new BeneficiaireRepresentation(p.getName(), getPourcentageFormat(p.getPart())))
                .toList();
    }

    private static String getPourcentageFormat(float part){
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(2);
        return percentFormat.format(part);

    }
}
