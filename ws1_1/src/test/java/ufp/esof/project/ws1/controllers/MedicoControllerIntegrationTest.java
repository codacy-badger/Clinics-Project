package ufp.esof.project.ws1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import ufp.esof.project.ws1.Ws1Application;
import ufp.esof.project.ws1.models.Medico;
import ufp.esof.project.ws1.services.interfaces.MedicoServiceI;
import ufp.esof.project.ws1.services.filters.FilterObject;
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
public class MedicoControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MedicoServiceI medicoService;

    @Autowired
    ObjectMapper mapper;

    @Before
    public void setUp(){

        Medico medico=new Medico("medico1", "address",LocalDate.of(1970,01,20), "12332", "especialidade","123", "email" );
        medicoService.save(medico);
    }

    @Test
    public void getAllMedico() throws Exception {
        Iterable<Medico> medicos=medicoService.getFilteredMedicos(new FilterObject());
        mvc.perform(get("/medico").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(medicos)))
                .andReturn();
    }

    @Test
    public void getById() throws Exception {
        Medico medico=medicoService.findMedicoById(1l).get();
        Assert.assertNotNull (medico);
        mvc.perform(get("/medico/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(medico.getName()))
                .andReturn();

        mvc.perform(get("/medico/error").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getByPhone() throws Exception {
        Medico medico=medicoService.getMedicoByPhone("123").get();
        mvc.perform(get("/medico/phone/123").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(medico.getName()))
                .andReturn();

        mvc.perform(get("/medico/phone/error").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void getByEmail() throws Exception {
        Medico medico=medicoService.getMedicoByEmail("email").get();
        Assert.assertNotNull (medico);
        mvc.perform(get("/medico/email/email").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(medico.getName()))
                .andReturn();

        mvc.perform(get("/medico/email/error").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());

    }

}
