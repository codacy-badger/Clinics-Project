package ufp.esof.project.ws1_1.services;

import ufp.esof.project.ws1_1.models.Cliente;
import ufp.esof.project.ws1_1.repositories.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufp.esof.project.ws1_1.services.interfaces.ClienteServiceI;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class ClienteService implements ClienteServiceI {

    @Autowired
    private ClienteRepo clienterepo;


    @Override
    public Optional<Cliente> findClienteById(Long id) {
        return clienterepo.findById(id);
    }

    @Override
    public Set<Cliente> findAllClientes() {
        Set<Cliente> clientes=new HashSet<>();
        for(Cliente cliente:this.clienterepo.findAll()){
            clientes.add(cliente);
        }
        return Collections.unmodifiableSet(clientes);
    }

    @Override
    public Optional<Cliente> getClienteByName(String name) {
        for(Cliente cliente:findAllClientes()){
            if (cliente.getName().equalsIgnoreCase(name)){
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cliente> getClienteByEmail(String email) {
        for(Cliente cliente:findAllClientes()){
            if (cliente.getEmail().equalsIgnoreCase(email)){
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cliente> getClienteByPhone(String tlm) {
        for(Cliente cliente:findAllClientes()){
            if (cliente.getTlm().equalsIgnoreCase(tlm)){
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }


    public Optional<Cliente> save (Cliente c) { clienterepo.save(c); return clienterepo.findById(c.getId());}
}
