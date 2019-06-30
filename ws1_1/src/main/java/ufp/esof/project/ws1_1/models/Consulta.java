package ufp.esof.project.ws1_1.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.DayOfWeek;
import java.time.LocalTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Consulta extends BaseModel {

    private LocalTime horario;

    private String especialidade;
    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Cliente cliente;

    private String consultorio;

    private DayOfWeek dia;


    public Consulta(LocalTime horario, String especialidade, Cliente cliente, Medico medico, String consultorio, DayOfWeek dia) {




        if (medico.getEspecialidade().equalsIgnoreCase(especialidade)) {
            this.horario = horario;
            this.especialidade = medico.getEspecialidade();
            this.cliente = cliente;
            this.medico = medico;
            this.consultorio = consultorio;
            this.dia=dia;
        } else return;

    }
}