package ufp.esof.project.ws1.models;

import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MedicoTest {

    private Cliente cliente1 = new Cliente ("Leandro", "Meixomil", LocalDate.of (1998, 04, 17), "12344", "911", "leandro@gmail.com");


    private Medico medico1 = new Medico ("Alberto", "Porto", LocalDate.of (1970, 01, 20), "12332", "Urologia", "9119", "alberto@gmail.com");

    private Horario h1 = new Horario (DayOfWeek.MONDAY, LocalTime.of (9, 0), LocalTime.of (20, 0), medico1);

    private List<Horario> horarios = new ArrayList<> ();

    @Before
    public void setUp() {

    }

    @Test
    public void testAddHorarios() {
        horarios.add (h1);

        assertEquals (0, medico1.getHorarios ().size ());
        medico1.addHorario (horarios);
        assertEquals (1, medico1.getHorarios ().size ());

    }

    @Test
    public void testAddConsulta() {

        //Consulta consulta1=new Consulta();

        horarios.add (h1);

        medico1.addHorario (horarios);

        assertEquals (0, medico1.getConsultas ().size ());
        medico1.addConsulta (LocalTime.of (10, 0), cliente1, "A134", DayOfWeek.MONDAY,20.0);
        assertEquals (1, medico1.getConsultas ().size ());
        medico1.addConsulta (LocalTime.of (10, 0), cliente1, "A134", DayOfWeek.MONDAY,20.0);
        assertEquals (1, medico1.getConsultas ().size ());

    }

}
