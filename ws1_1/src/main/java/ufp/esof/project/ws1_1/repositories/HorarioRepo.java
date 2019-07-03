package ufp.esof.project.ws1_1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ufp.esof.project.ws1_1.models.Horario;

@Repository
public interface HorarioRepo extends CrudRepository<Horario, Long> {
}
