package com.exercie.api.actionnaire.adapter.exceptionsHandler;

import com.exercie.api.actionnaire.domain.exceptions.AucunBeneficiaireTrouverException;
import com.exercie.api.actionnaire.domain.exceptions.EntrepriseNonTrouverException;
import com.exercie.api.actionnaire.domain.exceptions.IllegalScopeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BeneficaireExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntrepriseNonTrouverException.class)
    public ResponseEntity<BeneficiaireResponseError> entrepriseNotFoundException(EntrepriseNonTrouverException abt) {
       return new ResponseEntity<>(new BeneficiaireResponseError(abt.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AucunBeneficiaireTrouverException.class)
    public ResponseEntity<BeneficiaireResponseError> aucunBeneficiaireTrouverException(AucunBeneficiaireTrouverException ete) {
       return  new ResponseEntity<>(new BeneficiaireResponseError(ete.getMessage(), HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
    }
    @ExceptionHandler(IllegalScopeException.class)
    public ResponseEntity<BeneficiaireResponseError> illegalScopeException(IllegalScopeException ise) {
        return new ResponseEntity<>(new BeneficiaireResponseError(ise.getMessage(), HttpStatus.NOT_ACCEPTABLE.value()), HttpStatus.NOT_ACCEPTABLE);
    }

}
