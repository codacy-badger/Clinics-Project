package ufp.esof.project.ws2;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping(value = "/cliente/porto", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAllClientesPorto() {
        //String[] paths={"http://localhost:8080/cliente","http://localhost:8081/cliente"};
        //Map<String, String> map = new HashMap<String, String>();
        //map.put("porto",paths[0]);
        //map.put("lisboa",paths[1]);
        String path = "http://localhost:8080/cliente";
        HttpHeaders headers = new HttpHeaders ();
        HttpEntity<String> nullBodyRequest = new HttpEntity<> (null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest (path, HttpMethod.GET, nullBodyRequest, Iterable.class);


        return responseEntity.getBody ();
    }

    @GetMapping(value = "/cliente/lisboa", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAllClientesLisboa() {
        //String[] paths={"http://localhost:8080/cliente","http://localhost:8081/cliente"};
        //Map<String, String> map = new HashMap<String, String>();
        //map.put("porto",paths[0]);
        //map.put("lisboa",paths[1]);
        String path = "http://localhost:8081/cliente";
        HttpHeaders headers = new HttpHeaders ();
        HttpEntity<String> nullBodyRequest = new HttpEntity<> (null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest (path, HttpMethod.GET, nullBodyRequest, Iterable.class);


        return responseEntity.getBody ();
    }

    @GetMapping(value = "/medico/porto", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAllMedicosPorto() {
        String path = "http://localhost:8080/medico";
        HttpHeaders headers = new HttpHeaders ();
        HttpEntity<String> nullBodyRequest = new HttpEntity<> (null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest (path, HttpMethod.GET, nullBodyRequest, Iterable.class);


        return responseEntity.getBody ();
    }

    @GetMapping(value = "/medico/lisboa", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAllMedicosLisboa() {
        String path = "http://localhost:8081/medico";
        HttpHeaders headers = new HttpHeaders ();
        HttpEntity<String> nullBodyRequest = new HttpEntity<> (null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest (path, HttpMethod.GET, nullBodyRequest, Iterable.class);


        return responseEntity.getBody ();
    }

    /*
    @GetMapping(value="/medico/{id}/porto",produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getMedicoByIdPorto(@PathVariable("id") Long id){
        String path="http://localhost:8080/medico/";

        path.concat(id.toString());
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> nullBodyRequest=new HttpEntity<>(null,headers);
        ResponseEntity<Iterable> responseEntity=makeRequest(path, HttpMethod.GET,nullBodyRequest,Iterable.class);


        return responseEntity.getBody();
    }
    */

    @GetMapping(value = "/medico/{id}/porto", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMedicoByIdPorto() {
        return "redirect:http://localhost:8080/medico/{id}";
    }

    @GetMapping(value = "/medico/{id}/lisboa", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMedicoByIdLisboa() {
        return "redirect:http://localhost:8081/medico/{id}";
    }

    /**
     * TODO
     */
    @GetMapping(value = "/cliente/{id}/porto", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getClienteByIdPorto() {
        return "redirect:http://localhost:8080/cliente/{id}";
    }

    @GetMapping(value = "/cliente/{id}/lisboa", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getClienteByIdLisboa() {
        return "redirect:http://localhost:8081/cliente/{id}";
    }


    @GetMapping(value = "/consulta/porto", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAllConsultasPorto() {
        // String[] path={"http://localhost:8080/consulta","http://localhost:8081/consulta"};
        String path = "http://localhost:8080/consulta";
        HttpHeaders headers = new HttpHeaders ();
        HttpEntity<String> nullBodyRequest = new HttpEntity<> (null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest (path, HttpMethod.GET, nullBodyRequest, Iterable.class);
        return responseEntity.getBody ();
    }

    @GetMapping(value = "/consulta/lisboa", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAllConsultasLisboa() {
        // String[] path={"http://localhost:8080/consulta","http://localhost:8081/consulta"};
        String path = "http://localhost:8081/consulta";
        HttpHeaders headers = new HttpHeaders ();
        HttpEntity<String> nullBodyRequest = new HttpEntity<> (null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest (path, HttpMethod.GET, nullBodyRequest, Iterable.class);
        return responseEntity.getBody ();
    }


    @PostMapping(value = "/consulta/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable saveConsulta() {
        // String[] path={"http://localhost:8080/cliente","http://localhost:8081/cliente"};
        String path = "http://localhost:8080/cliente";
        HttpHeaders headers = new HttpHeaders ();
        HttpEntity<String> nullBodyRequest = new HttpEntity<> (null, headers);
        ResponseEntity<Iterable> responseEntity = makeRequest (path, HttpMethod.POST, nullBodyRequest, Iterable.class);
        return responseEntity.getBody ();
    }


    private ResponseEntity makeRequest(String path, HttpMethod method, HttpEntity request, Class responseType) {
        RestTemplate restTemplate = new RestTemplate ();
        return restTemplate.exchange (path, method, request, responseType);
    }
}
