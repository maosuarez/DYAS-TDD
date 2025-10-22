package edu.unisabana.tyvs.tdd.domain.service;

import edu.unisabana.tyvs.tdd.domain.model.*;
import junit.framework.TestCase;

public class RegistryTest extends TestCase {

    public void testShouldRegisterValidPerson() {
        Registry registry = new Registry();
        Person person = new Person("Ana", 1, 30, Gender.FEMALE, true);
        RegisterResult result = registry.registerVoter(person);
        assertEquals(RegisterResult.VALID, result);
    }

    public void shouldReturnInvalidWhenPersonIsNull(){
        Registry registry = new Registry();
        RegisterResult result = registry.registerVoter(null);
        assertEquals(RegisterResult.INVALID, result);
    }

    public void testShouldRejectDeadPerson() {
        Registry registry = new Registry();
        Person dead = new Person("Carlos", 2, 40, Gender.MALE, false);
        RegisterResult result = registry.registerVoter(dead);
        assertEquals(RegisterResult.DEAD, result);
    }

    public void testShouldRejectNotBornPerson(){
        Registry registry = new Registry();
        Person notBorn = new Person("Gabriela", 3, -23, Gender.FEMALE, true);
        RegisterResult result = registry.registerVoter(notBorn);
        assertEquals(RegisterResult.INVALID_AGE, result);
    }

    public void shouldRejectUnderageAt17() {
        Registry registry = new Registry();
        Person junior = new Person("Nicolas", 4, 17, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(junior);
        assertEquals(RegisterResult.UNDERAGE, result);
    }

    public void shouldAcceptAdultAt18(){
        Registry registry = new Registry();
        Person adultPerson = new Person("Nicolas", 4, 18, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(adultPerson);
        assertEquals(RegisterResult.VALID, result);
    }

    public void shouldAcceptMaxAge120(){
        Registry registry = new Registry();
        Person probDeadPerson = new Person("Isabela", 5, 130, Gender.FEMALE, true);
        RegisterResult result = registry.registerVoter(probDeadPerson);
        assertEquals(RegisterResult.INVALID_AGE, result);
    }

    public void shouldRejectInvalidAgeOver120(){
        Registry registry = new Registry();
        Person probDeadPerson = new Person("Isabela", 5, 130, Gender.FEMALE, true);
        RegisterResult result = registry.registerVoter(probDeadPerson);
        assertEquals(RegisterResult.INVALID_AGE, result);
    }

    public void shouldRejectWhenIdIsZeroOrNegative(){
        Registry registry = new Registry();
        Person invalidIdPerson = new Person("Pablo", -5, 30, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(invalidIdPerson);
        assertEquals(RegisterResult.INVALID_ID, result);
    }

    public void testShouldRejectDuplicated() {
        Registry registry = new Registry();
        Person person01 = new Person("Ana", 1, 30, Gender.FEMALE, true);
        RegisterResult result01 = registry.registerVoter(person01);
        Person person02 = new Person("Juan", 1, 30, Gender.MALE, true);
        RegisterResult result02 = registry.registerVoter(person02);
        assertEquals(RegisterResult.DUPLICATED, result02);
    }



}


