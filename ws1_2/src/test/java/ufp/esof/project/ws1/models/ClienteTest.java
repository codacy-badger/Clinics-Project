package ufp.esof.project.ws1.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ClienteTest {

    private Consulta consulta1 = new Consulta ();

    private Cliente cliente1 = new Cliente ();

    @Before
    public void setUp() {

    }

    @Test
    public void testAddConsulta() {
        assertEquals (0, cliente1.getConsultas ().size ());
        cliente1.addConsulta (consulta1);
        assertEquals (1, cliente1.getConsultas ().size ());
    }

}
