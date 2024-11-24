package com.exercie.api.actionnaire.usecase.gateway;

import com.exercie.api.actionnaire.domain.Societe;

import java.util.List;

public interface SocieteDataAccessGateway {
    List<Societe> getSocietes();
}
