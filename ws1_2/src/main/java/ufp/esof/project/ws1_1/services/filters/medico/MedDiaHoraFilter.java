package ufp.esof.project.ws1_1.services.filters.medico;

import ufp.esof.project.ws1_1.models.Consulta;
import ufp.esof.project.ws1_1.models.Horario;
import ufp.esof.project.ws1_1.models.Medico;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class MedDiaHoraFilter implements MedFilterI {

    private String diaToFilter;
    private int horaTofilter;

    public MedDiaHoraFilter(String diaToFilter, int horaTofilter){
        this.diaToFilter=diaToFilter;
        this.horaTofilter=horaTofilter;
    }

    @Override
    public Set<Medico> filter(Set<Medico> medicos) {

        int count=0;

        Set<Medico> medicos_aux=new HashSet<>();

        if(diaToFilter==null)return medicos;



        for(Medico m : medicos) {
            int day_index=DayOfWeek.valueOf(diaToFilter).getValue();
            Horario horario_med = m.getHorarios().get(day_index-1);

            if(horaTofilter<horario_med.getHora_incio().getHour() || horaTofilter>=horario_med.getHora_fim().getHour()){
                continue;
            }
            count=0;
            if (m.getConsultas().isEmpty()) {
                medicos_aux.add(m);
            } else {
                for (Consulta c : m.getConsultas()) {
                    if (c.getDia().equals(DayOfWeek.valueOf(diaToFilter))) {
                        if ((c.getHorario().equals(LocalTime.of(horaTofilter, 0)))) {
                            count++;

                        }
                    }
                }
                if(count==0){
                    medicos_aux.add(m);
                }
            }
        }
        return medicos_aux;

    }

}