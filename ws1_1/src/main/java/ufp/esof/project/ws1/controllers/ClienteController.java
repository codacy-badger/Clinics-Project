package ufp.esof.project.ws1.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ufp.esof.project.ws1.models.Cliente;
import ufp.esof.project.ws1.services.ClienteService;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/cliente")
public class ClienteController {


    private ClienteService clienteService;


    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Set<Cliente> findAllClientes() {
        return clienteService.findAllClientes ();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Cliente getById(@PathVariable("id") Long id) {
        Optional<Cliente> clienteDTOOptional = clienteService.findClienteById (id);
        return clienteDTOOptional.orElse (null);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> getByName(@PathVariable("name") String name) {
        Optional<Cliente> clienteDTOOptional = clienteService.getClienteByName (name);
        if (clienteDTOOptional.isPresent ()) {
            return ResponseEntity.ok (clienteDTOOptional.get ());
        }
        return ResponseEntity.notFound ().build ();
    }

    @RequestMapping(value = "/phone/{phone}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> getByPhone(@PathVariable("phone") String phone) {
        Optional<Cliente> clienteDTOOptional = clienteService.getClienteByPhone (phone);
        if (clienteDTOOptional.isPresent ()) {
            return ResponseEntity.ok (clienteDTOOptional.get ());
        }
        return ResponseEntity.notFound ().build ();
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> getByEmail(@PathVariable("email") String email) {
        Optional<Cliente> clienteDTOOptional = clienteService.getClienteByEmail (email);
        if (clienteDTOOptional.isPresent ()) {
            return ResponseEntity.ok (clienteDTOOptional.get ());
        }
        return ResponseEntity.notFound ().build ();
    }

    @PostMapping("/criar")
    public ResponseEntity<Cliente> createClient(@RequestBody Cliente clienteInfo) {
        Optional<Cliente> clienteOptional = clienteService.save (clienteInfo);
        if (clienteOptional.isPresent ()) {
            return ResponseEntity.ok (clienteOptional.get ());
        }
        return ResponseEntity.notFound ().build ();
    }

}
