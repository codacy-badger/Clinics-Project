package ufp.esof.project.ws1_1.services.filters.medico;

import org.springframework.stereotype.Service;
import ufp.esof.project.ws1_1.models.Medico;
import ufp.esof.project.ws1_1.services.filters.AndFilter;
import ufp.esof.project.ws1_1.services.filters.FilterI;
import ufp.esof.project.ws1_1.services.filters.FilterObject;

import java.util.Set;

@Service
public class MedFilterService {
    public Set<Medico> filterMedicos(Set<Medico> medicos, FilterObject filterObject) {

        FilterI<Medico> medicoEspecialidadeFilter = new MedEspecialidadeFilter(filterObject.getEspecialidade());

        FilterI<Medico> medicoDiaHoraFilter = new MedDiaHoraFilter(filterObject.getDia(),filterObject.getHora());

        FilterI<Medico> medicoAndHorarioFilter = new AndFilter<> (medicoEspecialidadeFilter, medicoDiaHoraFilter);

        return medicoAndHorarioFilter.filter(medicos);
    }
}
