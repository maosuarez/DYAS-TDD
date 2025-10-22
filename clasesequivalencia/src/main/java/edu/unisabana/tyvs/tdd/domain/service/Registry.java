package edu.unisabana.tyvs.tdd.domain.service;

import java.util.ArrayList;
import java.util.List;

import edu.unisabana.tyvs.tdd.domain.model.*;
import edu.unisabana.tyvs.tdd.domain.model.RegisterResult;

public class Registry {
    // Lista de inscritos
    private List<Person> registeredList = new ArrayList<>();

    // Constantes
    int MIN_AGE = 18;
    int MAX_AGE = 120;

    // Funcion de validacion
    public RegisterResult registerVoter(Person p) {
        // Validacion de seguridad ante nulos
        if (p == null) return RegisterResult.INVALID;
        // No pueden votar las personas muertas
        if (!p.isAlive()) return RegisterResult.DEAD;
        // Personas que no han nacido no votan
        if (p.getAge() < 0) return RegisterResult.INVALID_AGE;
        // Menores de edad imposible votar
        if (p.getAge() < MIN_AGE) return RegisterResult.UNDERAGE;
        // No pueden votar personas muy viejas
        if (p.getAge() > MAX_AGE) return RegisterResult.INVALID_AGE;
        // Restringir a ID's validos
        if (p.getId() <= 0) return RegisterResult.INVALID_ID;
        // Validar existencia previa de la persona
        for (Person currentPerson : registeredList) {
            if (currentPerson.getId() == p.getId()) {
                return RegisterResult.DUPLICATED;
            }
        }
        // Agregar a la lsita en caso de cumplir
        registeredList.add(p);
        // Devolver el estado de la persona valida
        return RegisterResult.VALID;
    }
}
