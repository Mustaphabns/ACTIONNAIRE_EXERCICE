package com.exercie.api.actionnaire.adapter.controller;

import com.exercie.api.actionnaire.adapter.representation.BeneficiaireRepresentation;

import com.exercie.api.actionnaire.usecase.RecupererBeneficiaireUsecase;
import com.exercie.api.actionnaire.usecase.Scope;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/beneficiaire")
public class BeneficiaireController {

    private final RecupererBeneficiaireUsecase recupererBeneficiaireUsecase;

    @GetMapping
    public List<BeneficiaireRepresentation> rechercher(@RequestParam int id,
                                                       @RequestParam(defaultValue = "ALL")
                                                        String scope){
        var beneficiaresDomain = recupererBeneficiaireUsecase.getBeneficiaires(id, scope).stream().toList();
        return BeneficiaireRepresentation.fromDomain(beneficiaresDomain);
    }
}
