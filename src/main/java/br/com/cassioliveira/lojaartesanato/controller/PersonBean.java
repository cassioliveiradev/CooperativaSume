package br.com.cassioliveira.lojaartesanato.controller;

import br.com.cassioliveira.lojaartesanato.enumerations.Documentation;
import br.com.cassioliveira.lojaartesanato.enumerations.Gender;
import br.com.cassioliveira.lojaartesanato.enumerations.States;
import br.com.cassioliveira.lojaartesanato.services.PersonServices;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
@Model
public class PersonBean {
    
    @Inject
    private PersonServices personServices;

    private final List<States> states;
    private final List<Gender> genders;
    private final List<Documentation> documentations;
    static transient List<String> cities = new ArrayList<>();

    public PersonBean() {
        //Initializing list and receiving the valus of States Enumeration as a List.
        states = Arrays.asList(States.values());
        genders = Arrays.asList(Gender.values());
        documentations = Arrays.asList(Documentation.values());
    }
    
    public List<String> getReturnCountries() {
        return personServices.returnCountries();
    }
    
    public List<States> getStates() {
        return states;
    }

    public List<Gender> getGenders() {
        return genders;
    }

    public List<Documentation> getDocumentations() {
        return documentations;
    }

    public List<String> getCities() {
        return cities;
    }

    public PersonServices getPersonServices() {
        return personServices;
    }
}
