package com.kata.testing.mockito;

import com.kata.testing.common.Chef;
import com.kata.testing.common.ChefResponse;
import com.kata.testing.mockito.resources.ChefEntity;
import com.kata.testing.mockito.resources.IChefRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class MockitoChefServiceTest {

    @Mock
    private IChefRepository repository;

    @InjectMocks
    private MockitoChefService service;

    @Test
    void findChef_findChefInDatabase_obtainedChef() {
        // given
        Chef chef = new Chef("Sandra","Fernandez", LocalDate.of(2005,10,4));
        ChefEntity chefEntity = new ChefEntity("Sandra","Fernandez", LocalDate.of(2005,10,4));
        Mockito.when(repository.findByNameAndSurname(any(String.class),any(String.class))).thenReturn(Optional.of(chefEntity));
        ChefResponse expected = new ChefResponse("Sandra","Fernandez", LocalDate.of(2005,10,4));
        // when
        ChefResponse actual = service.findChef(chef);
        //then
        Assertions.assertEquals(expected.getName(),actual.getName());
        Assertions.assertEquals(expected.getSurname(),actual.getSurname());
        Assertions.assertEquals(expected.getBirthday(),actual.getBirthday());
    }

    @Test
    void findChef_findChefInDatabase_obtainedNull() {
        // given
        Chef chef = new Chef("Sandra","Fernandez", LocalDate.of(2005,10,4));
        Mockito.when(repository.findByNameAndSurname(any(String.class),any(String.class))).thenReturn(Optional.empty());
        ChefResponse expected = null;
        // when
        ChefResponse actual = service.findChef(chef);
        //then
        Assertions.assertNull(actual);
    }

    @Test
    void findAllPersons_findAllChefsInDatabase_obtainListOfChefs(){
        // given
        ChefEntity chefEntity = new ChefEntity("Sandra","Fernandez", LocalDate.of(2005,10,4));
        Mockito.when(repository.findAll()).thenReturn(List.of(chefEntity));
        List<ChefResponse> expected = List.of(new ChefResponse("Sandra","Fernandez",LocalDate.of(2005,10,4)));
        // when
        List<ChefResponse> actual = service.findAllPersons();
        // then
        Assertions.assertEquals(expected.size(),actual.size());
        Assertions.assertEquals(expected.get(0).getName(),actual.get(0).getName());
        Assertions.assertEquals(expected.get(0).getSurname(),actual.get(0).getSurname());
        Assertions.assertEquals(expected.get(0).getBirthday(),actual.get(0).getBirthday());
    }

    @Test
    void save_saveChefInDatabase_chefSaved() {
        // given
        Chef chef = new Chef("Sandra","Fernandez", LocalDate.of(2005,10,4));
        ChefEntity chefEntity = new ChefEntity("Sandra","Fernandez", LocalDate.of(2005,10,4));
        Mockito.when(repository.save(any(ChefEntity.class))).thenReturn(chefEntity);
        String expected = "Has guardado correctamente al chef Sandra Fernandez";
        // when
        String actual = service.save(chef);
        // then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void delete_deleteChefFromDatabase_chefDeleted(){
        // given
        Chef chef = new Chef("Sandra","Fernandez", LocalDate.of(2005,10,4));
        ChefEntity expected = new ChefEntity("Sandra","Fernandez", LocalDate.of(2005,10,4));
        // when
        service.delete(chef);
        // then
        Mockito.verify(repository).delete(Mockito.refEq(expected));
    }

    @Test
    void chefExists_chefExistInDatabase_chefExist() {
        // given
        Chef chef = new Chef("Sandra","Fernandez", LocalDate.of(2005,10,4));
        Mockito.when(repository.existsByNameAndSurname(any(String.class),any(String.class))).thenReturn(true);
        // when
        boolean actual = service.chefExist(chef);
        // then
        Assertions.assertTrue(actual);
    }

    @Test
    void chefExists_chefExistInDatabase_chefDoesNotExist() {
        // given
        Chef chef = new Chef("Sandra","Fernandez", LocalDate.of(2005,10,4));
        Mockito.when(repository.existsByNameAndSurname(any(String.class),any(String.class))).thenReturn(false);
        // when
        boolean actual = service.chefExist(chef);
        // then
        Assertions.assertFalse(actual);
    }


}
