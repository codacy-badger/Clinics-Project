package ufp.esof.project.ws1.services;

import org.springframework.beans.factory.annotation.Autowired;
import ufp.esof.project.ws1.models.Horario;
import ufp.esof.project.ws1.repositories.HorarioRepo;
import ufp.esof.project.ws1.services.interfaces.HorarioServiceI;

public class HorarioService implements HorarioServiceI {

    @Autowired
    private HorarioRepo horariorepo;

    @Override
    public void save(Horario horario) {
        horariorepo.save (horario);
    }
}
