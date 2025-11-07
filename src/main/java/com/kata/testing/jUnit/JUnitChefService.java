package com.kata.testing.jUnit;

import com.kata.testing.common.Chef;
import com.kata.testing.common.ChefResponse;
import com.kata.testing.common.IService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JUnitChefService implements IService {

    private List<Chef> chefsWithMichelinStart;

    public JUnitChefService(List<Chef> chefsWithMichelinStart) {
        this.chefsWithMichelinStart = chefsWithMichelinStart;
    }

    /**
     * En este método usamos la opción filter de los streams de java 8 para recorrer la lista,
     * reducimos la lista a un único objeto el cual debe coincidir con las condiciones de dentro
     * del filter en este caso está paremetrizado el usuario para que puedas buscar cualquier chef,
     * siempre y cuando se cumpla que le nombre y el apellido existe en la lista inicializada
     * en el método initializeAllChefs()
     *
     * @return te devuelve el chef en formato ChefResponse
     * ¡¡IMPORTANTE ESTE MÉTODO DEVUELVE 2 POSIBLES RESPUESTAS, UN OBJETO DE TIPO CHEF Y UN NULL!!
     */
    @Override
    public ChefResponse findChef(Chef chefToSearch) {
        Optional<Chef> chefOptional = chefsWithMichelinStart.stream()
                .filter(chef -> chef.getName().equals(chefToSearch.getName())
                        && chef.getSurname().equals(chefToSearch.getSurname()))
                .findFirst();
        return chefOptional.map(this::createResponse).orElse(null);
    }

    /**
     * Este método sirve devolver todos los chefs que hay en la lista
     *
     * @return devuelve la lista completa de todos los chefs en formato ChefResponse
     */
    @Override
    public List<ChefResponse> findAllPersons() {
        return chefsWithMichelinStart.stream()
                .map(this::createResponse)
                .collect(Collectors.toList());
    }

    /**
     * Este método sirve para añadir un chef al listado
     *
     * @param chef, este parametro consta de, nombre, apellido y fecha de nacimiento
     * @return envia un mensaje indicando que se ha guardado correctamente.
     */
    @Override
    public String save(Chef chef) {
        chefsWithMichelinStart.add(chef);
        return "Has guardado correctamente a " + chef.getName() + " " + chef.getSurname();
    }

    /**
     * Este método sirve para borrar un chef del listado
     *
     * @param chef, este parametro consta de, nombre, apellido y fecha de nacimiento
     */
    @Override
    public void delete(Chef chef) {
        chefsWithMichelinStart.remove(chef);
    }

    /**
     * Este método sirve para comprobar si un chef existe en el listado o no
     *
     * @return devuelve un boolean indicando si el chef existe o no
     */
    @Override
    public boolean chefExist(Chef chef) {
        return chefsWithMichelinStart.contains(chef);
    }

    /**
     * Método privado creado para mapear de Chef a ChefResponse
     *
     * @param chef este parametro consta de, nombre, apellido y fecha de nacimiento
     * @return devuelve el chef que has introducido en formato ChefResponse
     */
    private ChefResponse createResponse(Chef chef) {
        return new ChefResponse(chef.getName(), chef.getSurname(), chef.getBirthday());
    }
}
