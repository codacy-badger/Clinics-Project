package ufp.esof.project.ws1_1.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import ufp.esof.project.ws1_1.models.Cliente;

import java.util.Optional;

@Repository
public interface ClienteRepo extends CrudRepository<Cliente, Long> {
    Optional<Cliente> findByName(String name);

}
