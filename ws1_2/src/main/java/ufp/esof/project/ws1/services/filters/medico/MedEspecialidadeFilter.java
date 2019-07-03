package ufp.esof.project.ws1.services.filters.medico;

import ufp.esof.project.ws1.models.Medico;

import java.util.Set;
import java.util.stream.Collectors;

public class MedEspecialidadeFilter implements MedFilterI {

    private String especialidadeToFilter;

    public MedEspecialidadeFilter(String especialidadeToFilter) {
        this.especialidadeToFilter = especialidadeToFilter;
    }

    @Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if (especialidadeToFilter == null) return medicos;
        return medicos.stream ()
                .filter (medico -> medico.getEspecialidade ().equalsIgnoreCase (this.especialidadeToFilter))
                .collect (Collectors.toSet ());
    }


}
