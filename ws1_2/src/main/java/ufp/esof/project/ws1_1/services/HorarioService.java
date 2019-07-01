package ufp.esof.project.ws1_1.services;

import org.springframework.beans.factory.annotation.Autowired;
import ufp.esof.project.ws1_1.models.Horario;
import ufp.esof.project.ws1_1.repositories.HorarioRepo;
import ufp.esof.project.ws1_1.services.interfaces.HorarioServiceI;

public class HorarioService implements HorarioServiceI {

    @Autowired
    private HorarioRepo horariorepo;

    @Override
    public void save(Horario horario) {
        horariorepo.save(horario);
    }
}
