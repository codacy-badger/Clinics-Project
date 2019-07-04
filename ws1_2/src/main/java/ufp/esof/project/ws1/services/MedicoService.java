package ufp.esof.project.ws1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufp.esof.project.ws1.models.Medico;
import ufp.esof.project.ws1.repositories.MedicoRepo;
import ufp.esof.project.ws1.services.filters.FilterObject;
import ufp.esof.project.ws1.services.filters.medico.MedFilterService;
import ufp.esof.project.ws1.services.interfaces.MedicoServiceI;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MedicoService implements MedicoServiceI {

    private MedFilterService medicoFilterService;

    @Autowired
    private MedicoRepo medicorepo;


    public MedicoService(MedFilterService medicoFilterService) {
        this.medicoFilterService = medicoFilterService;
    }

    @Override
    public Optional<Medico> findMedicoById(Long id) {

        return medicorepo.findById (id);
    }

    @Override
    public Set<Medico> findAllMedicos() {
        Set<Medico> medicos = new HashSet<> ();
        for (Medico medico : this.medicorepo.findAll ()) {
            medicos.add (medico);
        }
        return Collections.unmodifiableSet (medicos);

    }

    @Override
    public Optional<Medico> getMedicoByName(String name) {

        for (Medico medico : findAllMedicos ()) {
            if (medico.getName ().equalsIgnoreCase (name)) {
                return Optional.of (medico);
            }
        }
        return Optional.empty ();
    }

    @Override
    public Optional<Medico> getMedicoByEmail(String email) {

        for (Medico medico : findAllMedicos ()) {
            if (medico.getEmail ().equalsIgnoreCase (email)) {
                return Optional.of (medico);
            }
        }
        return Optional.empty ();
    }

    @Override
    public Optional<Medico> getMedicoByPhone(String tlm) {

        for (Medico medico : findAllMedicos ()) {
            if (medico.getTlm ().equalsIgnoreCase (tlm)) {
                return Optional.of (medico);
            }
        }
        return Optional.empty ();
    }

    @Override
    public Set<Medico> getFilteredMedicos(FilterObject filterObject) {
        return medicoFilterService.filterMedicos (findAllMedicos (), filterObject);

    }

    @Override
    public Optional<Medico> save(Medico medico) {
        medicorepo.save (medico);
        return medicorepo.findById (medico.getId ());
    }

    @Override
    public boolean delete(Long id) {
        if (medicorepo.existsById (id)) {
            medicorepo.deleteById (id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Medico> getMedicoByCC(String cc) {

        for (Medico medico : findAllMedicos ()) {
            if (medico.getTlm ().equalsIgnoreCase (cc)) {
                return Optional.of (medico);
            }
        }
        return Optional.empty ();
    }
}

