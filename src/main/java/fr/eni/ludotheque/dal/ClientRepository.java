package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Set<Client> findByNomStartingWith(String search);
}