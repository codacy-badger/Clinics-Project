package ufp.esof.project.ws1_1.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ufp.esof.project.ws1_1.models.Consulta;
import ufp.esof.project.ws1_1.services.ConsultaService;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {


    private ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }


    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Set<Consulta> findAllConsultas() {
        return consultaService.findAllConsultas();
    }


    @RequestMapping(value="/cliente/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Set<Consulta>  getByClienteId(@PathVariable("id") Long id){

        return consultaService.getConsultaByClienteId(id);
    }


    @RequestMapping(value="/medico/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Set<Consulta>  getByMedicoId(@PathVariable("id") Long id){

        return consultaService.getConsultaByMedicoId(id);
    }


    @PostMapping(value = "/{medico_id}/{cliente_id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> saveConsulta(@RequestBody Consulta consulta, @PathVariable("medico_id") Long medico_id, @PathVariable("cliente_id") Long cliente_id){

        Optional<Consulta> consultaOptional= consultaService.saveConsulta(consulta,medico_id,cliente_id);
        if(consultaOptional.isPresent()){
            return ResponseEntity.ok(consultaOptional.get());
        }
        return ResponseEntity.notFound().build();

    }

}
