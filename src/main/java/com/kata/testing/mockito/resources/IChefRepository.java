package com.kata.testing.mockito.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IChefRepository extends JpaRepository<ChefEntity, Integer> {

    Optional<ChefEntity> findByNameAndSurname(String name, String surname);

    Boolean existsByNameAndSurname(String name, String surname);
}
