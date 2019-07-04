package ufp.esof.project.ws1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ufp.esof.project.ws1.models.Consulta;
import ufp.esof.project.ws1.services.ConsultaService;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {


    private ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Set<Consulta> findAllConsultas() {
        return consultaService.findAllConsultas ();
    }


    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Set<Consulta> getByClienteId(@PathVariable("id") Long id) {

        return consultaService.getConsultaByClienteId (id);
    }


    @RequestMapping(value = "/medico/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Set<Consulta> getByMedicoId(@PathVariable("id") Long id) {

        return consultaService.getConsultaByMedicoId (id);
    }


    @PostMapping(value = "/{medico_id}/{cliente_id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> saveConsulta(@RequestBody Consulta consulta, @PathVariable("medico_id") Long medico_id, @PathVariable("cliente_id") Long cliente_id) {

        Optional<Consulta> consultaOptional = consultaService.saveConsulta (consulta, medico_id, cliente_id);
        if (consultaOptional.isPresent ()) {
            return ResponseEntity.ok (consultaOptional.get ());
        }
        return ResponseEntity.notFound ().build ();

    }

    @DeleteMapping(value = "/cancelar/{id}")
    public ResponseEntity<Long> cancelar(@PathVariable("id") Long id) {
        if (consultaService.delete (id)) {
            return new ResponseEntity<> (id, HttpStatus.OK);
        }
        return new ResponseEntity<> (id, HttpStatus.OK);
    }

    @RequestMapping(value = "/cliente/{cliente_id}/{consulta_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> getConsultaOfClienteById(@PathVariable("cliente_id") Long cliente_id, @PathVariable("consulta_id") Long consulta_id) {
        Optional<Consulta> consultaOptional = consultaService.getConsultaOfClienteById (cliente_id, consulta_id);
        if (consultaOptional.isPresent ()) {
            return ResponseEntity.ok (consultaOptional.get ());
        }
        return ResponseEntity.notFound ().build ();
    }

    @RequestMapping(value = "/medico/{medico_id}/{consulta_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> getConsultaOfMedicoById(@PathVariable("medico_id") Long medico_id, @PathVariable("consulta_id") Long consulta_id) {
        Optional<Consulta> consultaOptional = consultaService.getConsultaOfMedicoById (medico_id, consulta_id);
        if (consultaOptional.isPresent ()) {
            return ResponseEntity.ok (consultaOptional.get ());
        }
        return ResponseEntity.notFound ().build ();
    }

    @PutMapping(value = "/modificar/{consulta_id}/hora")
    public ResponseEntity<Consulta> alterarHoraConsulta(@PathVariable("consulta_id") Long consulta_id, @RequestParam("hora") String horario) {
        Optional<Consulta> consultaOptional = consultaService.alterarHoraConsulta (consulta_id, horario);
        if (consultaOptional.isPresent ()) {
            return ResponseEntity.ok (consultaOptional.get ());
        }
        return ResponseEntity.notFound ().build ();
    }

    @PutMapping(value = "/modificar/{consulta_id}/consultorio")
    public ResponseEntity<Consulta> alterarConsultorioConsulta(@PathVariable("consulta_id") Long consulta_id, @RequestParam("consultorio") String consultorio) {
        Optional<Consulta> consultaOptional = consultaService.alterarConsultorioConsulta (consulta_id, consultorio);
        if (consultaOptional.isPresent ()) {
            return ResponseEntity.ok (consultaOptional.get ());
        }
        return ResponseEntity.notFound ().build ();
    }

    @PutMapping(value = "/modificar/{consulta_id}/dia")
    public ResponseEntity<Consulta> alterarDiaConsulta(@PathVariable("consulta_id") Long consulta_id, @RequestParam("dia") String dia) {
        Optional<Consulta> consultaOptional = consultaService.alterarDiaConsulta (consulta_id, dia);
        if (consultaOptional.isPresent ()) {
            return ResponseEntity.ok (consultaOptional.get ());
        }
        return ResponseEntity.notFound ().build ();
    }

    @RequestMapping(value = "/dia")
    public @ResponseBody
    Set<Consulta> getByMedicoId(@RequestParam("dia") String dia) {
        return consultaService.findConsultasByDay (dia);
    }
}
