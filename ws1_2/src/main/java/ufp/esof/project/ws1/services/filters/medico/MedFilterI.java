package ufp.esof.project.ws1.services.filters.medico;

import ufp.esof.project.ws1.models.Medico;
import ufp.esof.project.ws1.services.filters.FilterI;

import java.util.Set;

public interface MedFilterI extends FilterI<Medico> {
    Set<Medico> filter(Set<Medico> entities);
}
