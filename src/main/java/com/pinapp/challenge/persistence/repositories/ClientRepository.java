package com.pinapp.challenge.persistence.repositories;

import com.pinapp.challenge.persistence.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c.edad from Client c ")
    Optional<List<Integer>> findAllAges();
}
