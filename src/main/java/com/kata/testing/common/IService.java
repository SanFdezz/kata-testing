package com.kata.testing.common;


import java.util.List;

public interface IService {

    ChefResponse findChef(Chef chef);

    List<ChefResponse> findAllPersons();

    String save(Chef chef);

    void delete(Chef chef);

    boolean chefExist(Chef chef);

}
