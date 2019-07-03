package ufp.esof.project.ws1_1.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ufp.esof.project.ws1_1.models.Medico;
import ufp.esof.project.ws1_1.services.MedicoService;
import ufp.esof.project.ws1_1.services.filters.FilterObject;

import java.time.LocalDate;
import java.util.Optional;


@Controller
@RequestMapping("/medico")
public class MedicoController {


    private MedicoService medicoService;

    private Logger logger = LoggerFactory.getLogger (MedicoController.class);

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Medico> getAllMedicos(@ModelAttribute FilterObject filterObject) {
        logger.info (filterObject.toString ());
        return medicoService.getFilteredMedicos (filterObject);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Medico getById(@PathVariable("id") Long id) {
        Optional<Medico> medicoDTOOptional = medicoService.findMedicoById (id);
        return medicoDTOOptional.orElse (null);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> getByName(@PathVariable("name") String name) {
        Optional<Medico> medicoDTOOptional = medicoService.getMedicoByName (name);
        if (medicoDTOOptional.isPresent ()) {
            return ResponseEntity.ok (medicoDTOOptional.get ());
        }
        return ResponseEntity.notFound ().build ();
    }

    @RequestMapping(value = "/phone/{phone}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> getByPhone(@PathVariable("phone") String phone) {
        Optional<Medico> medicoDTOOptional = medicoService.getMedicoByPhone (phone);
        if (medicoDTOOptional.isPresent ()) {
            return ResponseEntity.ok (medicoDTOOptional.get ());
        }
        return ResponseEntity.notFound ().build ();
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> getByEmail(@PathVariable("email") String email) {
        Optional<Medico> medicoDTOOptional = medicoService.getMedicoByEmail (email);
        if (medicoDTOOptional.isPresent ()) {
            return ResponseEntity.ok (medicoDTOOptional.get ());
        }
        return ResponseEntity.notFound ().build ();
    }

    @DeleteMapping(value = "/remover/{id}")
    public ResponseEntity<Long> deleteMedico(@PathVariable("id") Long id) {
        if (medicoService.delete (id)) {
            return new ResponseEntity<> (id, HttpStatus.OK);
        }
        return new ResponseEntity<> (id, HttpStatus.OK);
    }

    @PostMapping("/contratar")
    public ResponseEntity<Medico> createEmployee(@RequestBody Medico medicoInfo, @RequestParam("birthday")@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)LocalDate birthday)
    {
        medicoInfo.setDatanascimento (birthday);
        Optional<Medico> medicoOptional= medicoService.save (medicoInfo);
        if(medicoOptional.isPresent()){
            return ResponseEntity.ok(medicoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

}
