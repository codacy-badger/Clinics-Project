package ufp.esof.project.ws1_1.services;

import ufp.esof.project.ws1.models.Cliente;
import ufp.esof.project.ws1.repositories.ClienteRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ufp.esof.project.ws1.services.ClienteService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepo clienteRepo;

//    Course course=new Course("course1");

    private Set<Cliente> clientes1=new HashSet<>();

    @Before
    public void setUp() {
//        courseService =new CourseService(courseFilter,courseRepo,degreeRepo);
        clientes1.add(new Cliente());
        clientes1.add(new Cliente());
        clientes1.add(new Cliente());

        when(clienteRepo.findAll())
                .thenReturn(clientes1);


    }

    @Test
    public void crud() {
        assertEquals(3,clienteService.findAllClientes().size());
        Mockito.verify(clienteRepo, Mockito.times(1)).findAll();
    }

}