package ufp.esof.project.ws1_1.services.interfaces;

import ufp.esof.project.ws1_1.models.Medico;
import ufp.esof.project.ws1_1.services.filters.FilterObject;

import java.util.Optional;
import java.util.Set;

public interface MedicoServiceI {

    Optional<Medico> findMedicoById(Long id);

    Set<Medico> findAllMedicos();

    Optional<Medico> getMedicoByName(String name);

    Optional<Medico> getMedicoByEmail(String email);

    Optional<Medico> getMedicoByPhone(String tlm);

    Set<Medico> getFilteredMedicos(FilterObject filterObject);


    void save(Medico medico);
}
