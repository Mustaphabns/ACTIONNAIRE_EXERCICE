package com.exercie.api.actionnaire;

import com.exercie.api.actionnaire.domain.Action;
import com.exercie.api.actionnaire.domain.Beneficiaire;
import com.exercie.api.actionnaire.domain.Societe;
import com.exercie.api.actionnaire.usecase.RecupererBeneficiaireUsecase;
import com.exercie.api.actionnaire.usecase.annotation.DomainService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@ComponentScan(includeFilters = {@ComponentScan.Filter(classes = DomainService.class)})
public class ActionnaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActionnaireApplication.class, args);

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ActionnaireApplication.class);
		RecupererBeneficiaireUsecase myService = context.getBean(RecupererBeneficiaireUsecase.class);

		myService.beneficiaireTargets(1).forEach(System.out::println);

		context.close();
	}

	@Bean
	List<Societe> societes(){

		Beneficiaire beneficiaireYvette = new Beneficiaire.PersonnePhysique("Yvette");
		Beneficiaire beneficiaireYves = new Beneficiaire.PersonnePhysique("Yves");
		Beneficiaire beneficiaireXavier = new Beneficiaire.PersonnePhysique("Xavier");
		Beneficiaire beneficiaireZoeA = new Beneficiaire.PersonnePhysique("Zoé");
		Beneficiaire beneficiaireZoeB = new Beneficiaire.PersonnePhysique("Zoé");

		Beneficiaire beneficiaireSocieteB = new Beneficiaire.PersonneMorale("Societé B", List.of(
				new Action(beneficiaireZoeB, .5f),
				new Action(new Beneficiaire.PersonneMorale("Societé C", List.of(
						new Action(beneficiaireYvette, .9f),
						new Action(beneficiaireYves, .05f),
						new Action(new Beneficiaire.PersonneMorale("Societé D", List.of()), .05f)
				)), .5f)


		));


		Societe societeA = new Societe(1, "Société A", List.of(
				new Action(beneficiaireXavier, .3f),
				new Action(beneficiaireZoeA, .1f),
				new Action(beneficiaireSocieteB, .6f)
		));

		Societe societeB = new Societe(2, "Société B", List.of(
				new Action(beneficiaireZoeB, .5f),
				new Action(new Beneficiaire.PersonneMorale("Societé C", List.of(
						new Action(beneficiaireYvette, .9f),
						new Action(beneficiaireYves, .05f),
						new Action(new Beneficiaire.PersonneMorale("Societé D", List.of()), .05f)
				)), .5f)
		));

		Societe societeC = new Societe(4, "Société C", List.of(
				new Action(beneficiaireYvette, .9f),
				new Action(beneficiaireYves, .05f),
				new Action(new Beneficiaire.PersonneMorale("Societé D", List.of()), .05f)
		));

		Societe societeD = new Societe(3, "Société D", List.of());

		return List.of(societeA, societeB, societeC, societeD);
	}

}


