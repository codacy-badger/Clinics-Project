package ufp.esof.project.ws1_1.services.interfaces;

import ufp.esof.project.ws1_1.models.Consulta;

import java.util.Optional;
import java.util.Set;

public interface ConsultaServiceI {
    Optional<Consulta> findConsultaById(Long id);

    Set<Consulta> findAllConsultas();

    Set<Consulta> getConsultaByClienteId(Long id);

    Set<Consulta> getConsultaByMedicoId(Long id);

    Optional<Consulta> saveConsulta(Consulta consulta, Long medico_id, Long cliente_id);
}
