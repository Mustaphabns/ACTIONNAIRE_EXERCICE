package com.exercie.api.actionnaire.adapter.controller;

import com.exercie.api.actionnaire.adapter.representation.BeneficiaireRepresentation;

import com.exercie.api.actionnaire.usecase.RecupererBeneficiaireUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/api/beneficiaire")
@Tag(name = "Societe beneficiaire", description = "Consultation des beneficiaire des sociétés")
public class BeneficiaireController {

    private final RecupererBeneficiaireUsecase recupererBeneficiaireUsecase;

    @Operation(summary = "Recuperer les beneficiaires")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "liste des bénéficiaires avec les pourcentages de détention de capital"),
            @ApiResponse (responseCode = "204", description = "aucun bénéficiaire n’est trouvé pour l’entreprise"),
            @ApiResponse (responseCode = "404", description = "l’entreprise n’existe pas"),
            @ApiResponse (responseCode = "406", description = "paramettre scope ne respecte pas le contra il faut avoir: ALL, PERSONNE_PHYSIQUE, BENEFICIAIRE_EFFECTIFS"),
    })
    @GetMapping
    public List<BeneficiaireRepresentation> rechercher(@RequestParam @Valid int societeId,
                                                       @RequestParam(defaultValue = "ALL")
                                                       @Valid  String scope){
        var beneficiaresDomain = recupererBeneficiaireUsecase.getBeneficiaires(societeId, scope).stream().toList();
        return BeneficiaireRepresentation.fromDomain(beneficiaresDomain);
    }
}
