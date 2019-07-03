package ufp.esof.project.ws1_1.services.interfaces;

import ufp.esof.project.ws1_1.models.Cliente;

import java.util.Optional;
import java.util.Set;

public interface ClienteServiceI {

    Optional<Cliente> findClienteById(Long id);

    Set<Cliente> findAllClientes();

    Optional<Cliente> getClienteByName(String name);

    Optional<Cliente> getClienteByEmail(String email);

    Optional<Cliente> getClienteByPhone(String tlm);


    Optional<Cliente> save(Cliente cliente);


}
