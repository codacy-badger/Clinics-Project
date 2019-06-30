package ufp.esof.project.ws1_1;

import ufp.esof.project.ws1_1.models.*;
import ufp.esof.project.ws1_1.services.ClienteService;
import ufp.esof.project.ws1_1.services.ConsultaService;
import ufp.esof.project.ws1_1.services.MedicoService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    //private Logger logger= LoggerFactory.getLogger(Bootstrap.class);
    private ClienteService clienteService;
    private MedicoService medicoService;
    private ConsultaService consultaService;

    public Bootstrap(ClienteService clienteService, MedicoService medicoService, ConsultaService consultaService) {
        this.clienteService = clienteService;
        this.medicoService = medicoService;
        this.consultaService = consultaService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Cliente c1 = new Cliente ("Gabriel Ferreira", "Cinfães", LocalDate.of (1966, 03, 23), "12344", "915", "gabrielF@gmail.com");
        Cliente c2 = new Cliente ("Maria Pereira", "Viseu", LocalDate.of (1967, 01, 27), "43753", "935", "mariaP@gmail.com");
        Cliente c3 = new Cliente ("Tiago Costa", "Gaia", LocalDate.of (1991, 10, 10), "64485", "965", "tiagoC@gmail.com");


        clienteService.save (c1);
        clienteService.save (c2);
        clienteService.save (c3);


        Medico med1 = new Medico ("Alberto Ferreira", "Porto", LocalDate.of (1972, 01, 20), "12332", "Urologia", "9119", "alberto@gmail.com");
        Medico med2 = new Medico ("José Carvalho", "Matosinhos", LocalDate.of (1992, 03, 29), "12342", "Geriatria", "9154", "jose@gmail.com");
        Medico med3 = new Medico ("Joana Ribeiro", "Gaia", LocalDate.of (1968, 04, 16), "15324", "Cardiologia", "9543", "joana@gmail.com");


        Horario h1 = new Horario (DayOfWeek.MONDAY, LocalTime.of (9, 0), LocalTime.of (20, 0), med1);
        Horario h2 = new Horario (DayOfWeek.TUESDAY, LocalTime.of (10, 0), LocalTime.of (21, 0), med1);
        Horario h3 = new Horario (DayOfWeek.WEDNESDAY, LocalTime.of (14, 0), LocalTime.of (22, 0), med1);
        Horario h4 = new Horario (DayOfWeek.THURSDAY, LocalTime.of (9, 0), LocalTime.of (20, 0), med1);
        Horario h5 = new Horario (DayOfWeek.FRIDAY, LocalTime.of (8, 0), LocalTime.of (12, 0), med1);

        Horario h6 = new Horario (DayOfWeek.MONDAY, LocalTime.of (8, 0), LocalTime.of (19, 0), med2);
        Horario h7 = new Horario (DayOfWeek.TUESDAY, LocalTime.of (12, 0), LocalTime.of (19, 0), med2);
        Horario h8 = new Horario (DayOfWeek.WEDNESDAY, LocalTime.of (11, 0), LocalTime.of (22, 0), med2);
        Horario h9 = new Horario (DayOfWeek.THURSDAY, LocalTime.of (10, 0), LocalTime.of (19, 0), med2);
        Horario h10 = new Horario (DayOfWeek.FRIDAY, LocalTime.of (8, 0), LocalTime.of (16, 0), med2);

        Horario h11 = new Horario (DayOfWeek.MONDAY, LocalTime.of (10, 0), LocalTime.of (18, 0), med3);
        Horario h12 = new Horario (DayOfWeek.TUESDAY, LocalTime.of (9, 0), LocalTime.of (21, 0), med3);
        Horario h13 = new Horario (DayOfWeek.WEDNESDAY, LocalTime.of (10, 0), LocalTime.of (16, 0), med3);
        Horario h14 = new Horario (DayOfWeek.THURSDAY, LocalTime.of (10, 0), LocalTime.of (15, 0), med3);
        Horario h15 = new Horario (DayOfWeek.FRIDAY, LocalTime.of (9, 0), LocalTime.of (17, 0), med3);

        List<Horario> horarios = new ArrayList<> ();
        horarios.add (h1);
        horarios.add (h2);
        horarios.add (h3);
        horarios.add (h4);
        horarios.add (h5);

        List<Horario> horarios_2 = new ArrayList<> ();
        horarios_2.add (h6);
        horarios_2.add (h7);
        horarios_2.add (h8);
        horarios_2.add (h9);
        horarios_2.add (h10);

        List<Horario> horarios_3 = new ArrayList<> ();
        horarios_3.add (h11);
        horarios_3.add (h12);
        horarios_3.add (h13);
        horarios_3.add (h14);
        horarios_3.add (h15);


        med1.addHorario (horarios);
        med2.addHorario (horarios_2);
        med3.addHorario (horarios_3);


        med1.addConsulta (LocalTime.of (8, 0), c1, "A188", DayOfWeek.FRIDAY);
        med1.addConsulta (LocalTime.of (9, 0), c2, "A188", DayOfWeek.FRIDAY);
        med1.addConsulta (LocalTime.of (10, 0), c3, "A134", DayOfWeek.MONDAY);

        med2.addConsulta (LocalTime.of (14, 0), c2, "A134", DayOfWeek.TUESDAY);

        med3.addConsulta (LocalTime.of (13, 0), c1, "A134", DayOfWeek.THURSDAY);


        medicoService.save (med1);
        medicoService.save (med2);
        medicoService.save (med3);

    }

}