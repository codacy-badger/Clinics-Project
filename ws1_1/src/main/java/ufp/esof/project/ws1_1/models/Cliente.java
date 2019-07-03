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
public class Cliente extends Pessoa {

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    private List<Consulta> consultas = new ArrayList<> ();

    public Cliente(String name, String address, LocalDate birthday, String cc, String tlm, String email) {
        super (name, address, birthday, cc, tlm, email);
    }

    public void addConsulta(LocalTime horario, String especialidade, Medico medico, String consultorio, DayOfWeek dia) {
        Consulta consulta = new Consulta (horario, especialidade, this, medico, consultorio, dia);
        consultas.add (consulta);
        consulta.setCliente (this);
    }

    public void addConsulta(Consulta consulta) {
        consultas.add (consulta);
    }


}
