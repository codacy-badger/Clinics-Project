package ufp.esof.project.ws1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import ufp.esof.project.ws1.Ws1Application;
import ufp.esof.project.ws1.models.Cliente;
import ufp.esof.project.ws1.services.interfaces.ClienteServiceI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Ws1Application.class)
@AutoConfigureMockMvc
@Transactional
public class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ClienteServiceI clienteService;


    @Autowired
    ObjectMapper mapper;

    @Before
    public void setUp(){

        Cliente cliente=new Cliente("cliente1","address1", LocalDate.of(2000,01,01),"1234","123","email");
        clienteService.save(cliente);
    }

    @Test
    public void getAllCliente() throws Exception {
        Iterable<Cliente> clientes=clienteService.findAllClientes();
        Assert.assertNotNull (clientes);
        mvc.perform(get("/cliente").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(clientes)))
                .andReturn();
    }

    @Test
    public void getById() throws Exception {
        Cliente cliente=clienteService.findClienteById(1l).get();
        mvc.perform(get("/cliente/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(cliente.getName()))
                .andReturn();

        mvc.perform(get("/cliente/error").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getByPhone() throws Exception {
        Cliente cliente=clienteService.getClienteByPhone("123").get();
        mvc.perform(get("/cliente/phone/123").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(cliente.getName()))
                .andReturn();

        mvc.perform(get("/cliente/phone/error").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void getByEmail() throws Exception {
        Cliente cliente=clienteService.getClienteByEmail("email").get();
        Assert.assertNotNull (cliente);
        mvc.perform(get("/cliente/email/email").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(cliente.getName()))
                .andReturn();

        mvc.perform(get("/cliente/email/error").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());

    }

}