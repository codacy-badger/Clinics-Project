package ufp.esof.project.ws1.models;


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

    public Cliente(String name, String address, LocalDate datanascimento, String cc, String tlm, String email) {
        super (name, address, datanascimento, cc, tlm, email);
    }

    public void addConsulta(LocalTime horario, String especialidade, Medico medico, String consultorio, DayOfWeek dia, Double preco) {
        Consulta consulta = new Consulta (horario, especialidade, this, medico, consultorio, dia,preco);
        consultas.add (consulta);
        consulta.setCliente (this);
    }

    public void addConsulta(Consulta consulta) {
        consultas.add (consulta);
    }


}
