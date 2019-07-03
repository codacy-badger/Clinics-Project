package ufp.esof.project.ws1_1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Medico extends Pessoa {


    private String especialidade;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "medico")
    private List<Consulta> consultas = new ArrayList<> ();
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "medico")
    private List<Horario> horarios = new ArrayList<> ();

    public Medico(String name, String address, String birthday, String cc, String especialidade, String tlm, String email) {
        super (name, address, birthday, cc, tlm, email);
        this.especialidade = especialidade;

        System.out.println (this.getDatanascimento ());
        //this.horarios=horarios;
    }

    public Medico(String name, String address, LocalDate birthday, String cc, String especialidade, String tlm, String email) {
        super (name, address, birthday, cc, tlm, email);
        this.especialidade = especialidade;

        //this.horarios=horarios;

    }



    public void addConsulta(LocalTime horario, Cliente cliente, String consultorio, DayOfWeek dia) {

        int count = 0;
        int day_index = dia.getValue ();
        Horario horario_med = this.getHorarios ().get (day_index - 1);

        if (horario.getHour () < horario_med.getHora_incio ().getHour () || horario.getHour () >= horario_med.getHora_fim ().getHour ()) {
            return;
        }

        if (!this.getConsultas ().isEmpty ()) {
            for (Consulta c : this.getConsultas ()) {
                if (c.getDia ().equals (dia)) {
                    if ((c.getHorario ().equals (LocalTime.of (horario.getHour (), 0)))) {
                        count++;

                    }
                }
            }
            if (count != 0) {
                return;
            }
        }

        Consulta consulta = new Consulta (horario, this.especialidade, cliente, this, consultorio, dia);
        consultas.add (consulta);
        cliente.addConsulta (consulta);
    }

    public void addConsulta(Consulta consulta) {

        int count = 0;
        int day_index = consulta.getDia ().getValue ();
        Horario horario_med = this.getHorarios ().get (day_index - 1);

        if (consulta.getHorario ().getHour () < horario_med.getHora_incio ().getHour () || consulta.getHorario ().getHour () >= horario_med.getHora_fim ().getHour ()) {
            return;
        }

        if (this.getConsultas ().isEmpty ()) {
            consultas.add (consulta);
            return;
        } else {
            for (Consulta c : this.getConsultas ()) {
                if (c.getDia ().equals (consulta.getDia ())) {
                    if ((c.getHorario ().equals (LocalTime.of (consulta.getHorario ().getHour (), 0)))) {
                        count++;

                    }
                }
            }
            if (count == 0) {
                consultas.add (consulta);
                return;
            } else return;
        }
    }


        /*
        if (!consultas.isEmpty()) {
            for (Consulta c : consultas) {
                if(c.getDia())
                if (c.getHorario().equals(consulta.getHorario())) {
                    return;
                }
            }
        }
        consultas.add(consulta);
        //consulta.getCliente().addConsulta(consulta);

        */


    public void addHorario(List<Horario> horarios) {

        this.horarios = horarios;

    }

}
