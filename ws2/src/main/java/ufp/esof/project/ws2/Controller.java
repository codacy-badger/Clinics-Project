package ufp.esof.project.ws2;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ufp.esof.project.ws2.models.Consulta;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping(value = "/porto/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAllClientesPorto() {
        String path = "http://localhost:8080/cliente";
        HttpHeaders headers = new HttpHeaders ();
        HttpEntity<String> nullBodyRequest = new HttpEntity<> (null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest (path, HttpMethod.GET, nullBodyRequest, Iterable.class);
        return responseEntity.getBody ();
    }

    @GetMapping(value = "/lisboa/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAllClientesLisboa() {
        String path = "http://localhost:8081/cliente";
        HttpHeaders headers = new HttpHeaders ();
        HttpEntity<String> nullBodyRequest = new HttpEntity<> (null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest (path, HttpMethod.GET, nullBodyRequest, Iterable.class);
        return responseEntity.getBody ();
    }

    @GetMapping(value = "/porto/medico", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAllMedicosPorto() {
        String path = "http://localhost:8080/medico";
        HttpHeaders headers = new HttpHeaders ();
        HttpEntity<String> nullBodyRequest = new HttpEntity<> (null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest (path, HttpMethod.GET, nullBodyRequest, Iterable.class);


        return responseEntity.getBody ();
    }

    @GetMapping(value = "/lisboa/medico", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAllMedicosLisboa() {
        String path = "http://localhost:8081/medico";
        HttpHeaders headers = new HttpHeaders ();
        HttpEntity<String> nullBodyRequest = new HttpEntity<> (null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest (path, HttpMethod.GET, nullBodyRequest, Iterable.class);


        return responseEntity.getBody ();
    }

    @GetMapping(value = "/porto/medico/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMedicoByIdPorto() {
        return "redirect:http://localhost:8080/medico/{id}";
    }

    @GetMapping(value = "/lisboa/medico/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMedicoByIdLisboa() {
        return "redirect:http://localhost:8081/medico/{id}";
    }

    /**
     * TODO
     */
    @GetMapping(value = "/porto/cliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getClienteByIdPorto() {
        return "redirect:http://localhost:8080/cliente/{id}";
    }

    @GetMapping(value = "/lisboa/cliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getClienteByIdLisboa() {
        return "redirect:http://localhost:8081/cliente/{id}";
    }


    @GetMapping(value = "/porto/consulta", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAllConsultasPorto() {
        String path = "http://localhost:8080/consulta";
        HttpHeaders headers = new HttpHeaders ();
        HttpEntity<String> nullBodyRequest = new HttpEntity<> (null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest (path, HttpMethod.GET, nullBodyRequest, Iterable.class);
        return responseEntity.getBody ();
    }

    @GetMapping(value = "/clinica2/consulta", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAllConsultasLisboa() {
        String path = "http://localhost:8081/consulta";
        HttpHeaders headers = new HttpHeaders ();
        HttpEntity<String> nullBodyRequest = new HttpEntity<> (null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest (path, HttpMethod.GET, nullBodyRequest, Iterable.class);
        return responseEntity.getBody ();
    }


    @PostMapping(value = "/clinica1/consulta/{medico_id}/{cliente_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable saveConsulta(@RequestBody Consulta consulta, @PathVariable("medico_id") Long medico_id, @PathVariable("cliente_id") Long cliente_id) {
        String path = "http://localhost:8080/consulta";
        HttpHeaders headers = new HttpHeaders ();
        HttpEntity<Consulta> bodyRequest = new HttpEntity<> (consulta, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest (path, HttpMethod.POST, bodyRequest, Iterable.class);
        return responseEntity.getBody ();
    }


    private ResponseEntity makeRequest(String path, HttpMethod method, HttpEntity request, Class responseType) {
        RestTemplate restTemplate = new RestTemplate ();
        return restTemplate.exchange (path, method, request, responseType);
    }
}
