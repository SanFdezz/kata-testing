package com.kata.testing.mockito;

import com.kata.testing.common.Chef;
import com.kata.testing.common.ChefResponse;
import com.kata.testing.common.IService;
import com.kata.testing.mockito.resources.ChefEntity;
import com.kata.testing.mockito.resources.IChefRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MockitoChefService implements IService {

    private IChefRepository chefRepository;

    /**
     * Este método pedimos que nos devuelva un chef de la base de datos buscandolo por nombre y apellido
     *
     * @param chef, este parametro consta de, nombre, apellido y fecha de nacimiento
     * @return te devuelve el chef en formato ChefResponse
     * ¡¡IMPORTANTE ESTE MÉTODO DEVUELVE 2 POSIBLES RESPUESTAS, UN OBJETO DE TIPO CHEF Y UN NULL!!
     */
    @Override
    public ChefResponse findChef(Chef chef) {
        String name = chef.getName();
        String surname = chef.getSurname();
        Optional<ChefResponse> chefResponse = chefRepository.findByNameAndSurname(name, surname)
                .map(this::createResponse);
        return chefResponse.orElse(null);
    }

    /**
     * Este método sirve devolver todos los chefs que hay base de datos
     *
     * @return devuelve la lista completa de todos los chefs en formato ChefResponse
     */
    @Override
    public List<ChefResponse> findAllPersons() {
        return chefRepository.findAll().stream().map(this::createResponse).collect(Collectors.toList());
    }

    /**
     * Este método sirve para añadir un chef a la base de datos
     *
     * @param chef, este parametro consta de, nombre, apellido y fecha de nacimiento
     * @return envia un mensaje indicando que se ha guardado correctamente.
     */
    @Override
    public String save(Chef chef) {
        ChefEntity chefEntity = new ChefEntity(chef.getName(), chef.getSurname(), chef.getBirthday());
        chefRepository.save(chefEntity);
        return "Has guardado correctamente al chef " + chef.getName() + " " + chef.getSurname();
    }


    /**
     * Este método sirve para borrar un chef de la base de datos
     *
     * @param chef, este parametro consta de, nombre, apellido y fecha de nacimiento
     */
    @Override
    public void delete(Chef chef) {
        ChefEntity chefEntity = new ChefEntity(chef.getName(), chef.getSurname(), chef.getBirthday());
        chefRepository.delete(chefEntity);
    }

    /**
     * Este método sirve para comprobar si un chef existe en la base de datos
     *
     * @param chef, este parametro consta de, nombre, apellido y fecha de nacimiento
     * @return devuelve un boolean indicando si el chef existe o no en la base de datos
     */
    @Override
    public boolean chefExist(Chef chef) {
        String name = chef.getName();
        String surname = chef.getSurname();
        return chefRepository.existsByNameAndSurname(name, surname);
    }

    /**
     * Método privado creado para mapear de chefEntity a ChefResponse
     *
     * @param chef, este parametro consta de, nombre, apellido y fecha de nacimiento
     * @return te devuelve el chef en formato ChefResponse
     */
    private ChefResponse createResponse(ChefEntity chef) {
        return new ChefResponse(chef.getName(), chef.getSurname(), chef.getBirthday());
    }

}
