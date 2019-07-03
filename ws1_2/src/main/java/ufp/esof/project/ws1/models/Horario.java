package ufp.esof.project.ws1.models;

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
public class Horario extends BaseModel {

    private DayOfWeek dia;
    private LocalTime hora_incio;
    private LocalTime hora_fim;
    @ManyToOne
    private Medico medico;

    public Horario(DayOfWeek dia, LocalTime hora_incio, LocalTime hora_fim, Medico medico) {
        this.dia = dia;
        this.hora_incio = hora_incio;
        this.hora_fim = hora_fim;
        this.medico = medico;
    }

}
