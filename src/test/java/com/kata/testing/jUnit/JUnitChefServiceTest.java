package com.kata.testing.jUnit;

import com.kata.testing.common.Chef;
import com.kata.testing.common.ChefResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JUnitChefServiceTest {

    private JUnitChefService service;
    private List<Chef> chefList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        service = new JUnitChefService(chefList);
    }

    @Test
    void findChef_findChefInDatabase_obtainedChef() {
        // Given --> Que necesitamos para que el test pase
        Chef chef = new Chef("Sandra","Fernandez", LocalDate.of(2005,10,4));
        chefList.add(chef);
        ChefResponse expected = new ChefResponse("Sandra","Fernandez",LocalDate.of(2005,10,4));
        // When --> La invocacion del metodo que testeamos
        ChefResponse actualChef = service.findChef(chef);
        // Then --> Las asserciones
        Assertions.assertEquals(expected.getName(),actualChef.getName());
        Assertions.assertEquals(expected.getSurname(),actualChef.getSurname());
        Assertions.assertEquals(expected.getBirthday(),actualChef.getBirthday());
    }

    @Test
    void findChef_findChefInDatabase_obtainedNull(){
        // GIVEN
        Chef chef = new Chef("Sandra","Fernandez", LocalDate.of(2005,10,4));
        ChefResponse expected = null;
        // WHEN
        ChefResponse actualChef = service.findChef(chef);
        // THEN
        Assertions.assertEquals(expected,actualChef);
    }

    @Test
    void findAllPersons_findAllChefsInDatabase_obtainListOfChefs() {
        // GIVEN
        Chef chef = new Chef("Sandra","Fernandez", LocalDate.of(2005,10,4));
        chefList.add(chef);
        List<ChefResponse> expected = List.of(new ChefResponse("Sandra","Fernandez",LocalDate.of(2005,10,4)));
        // WHEN
        List<ChefResponse> actual = service.findAllPersons();
        // THEN
        Assertions.assertEquals(expected.size(),actual.size());
        Assertions.assertEquals(expected.get(0).getName(),actual.get(0).getName());
        Assertions.assertEquals(expected.get(0).getSurname(),actual.get(0).getSurname());
        Assertions.assertEquals(expected.get(0).getBirthday(),actual.get(0).getBirthday());

    }

    @Test
    void save_saveChefInDatabase_chefSaved() {
        //Given
        Chef chef = new Chef("Sandra","Fernandez", LocalDate.of(2005,10,4));
        String expected = "Has guardado correctamente a Sandra Fernandez";
        //When
        String actual = service.save(chef);
        //Then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void delete_deleteChefFromDatabase_chefDeleted() {
        //Given
        Chef chef = new Chef("Sandra","Fernandez", LocalDate.of(2005,10,4));
        chefList.add(chef);
        Assertions.assertTrue(chefList.contains(chef));
        //When
        service.delete(chef);
        //Then
        Assertions.assertFalse(chefList.contains(chef));
    }

    @Test
    void chefExists_chefExistInDatabase_chefExist() {
        // given
        Chef chef = new Chef("Sandra","Fernandez", LocalDate.of(2005,10,4));
        chefList.add(chef);
        boolean expected = true;
        // when
        boolean actual = service.chefExist(chef);
        // then
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void chefExists_chefExistInDatabase_chefDoesNotExist() {
        // given
        Chef chef = new Chef("Sandra","Fernandez", LocalDate.of(2005,10,4));
        boolean expected = false;
        // when
        boolean actual = service.chefExist(chef);
        // then
        Assertions.assertEquals(expected,actual);
    }



}
