package ufp.esof.project.ws1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ufp.esof.project.ws1.models.Cliente;

import java.util.Optional;

@Repository
public interface ClienteRepo extends CrudRepository<Cliente, Long> {
    Optional<Cliente> findByName(String name);

}
