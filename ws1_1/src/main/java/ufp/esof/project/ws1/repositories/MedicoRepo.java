package ufp.esof.project.ws1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ufp.esof.project.ws1.models.Medico;

@Repository
public interface MedicoRepo extends CrudRepository<Medico, Long> {

}