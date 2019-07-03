package ufp.esof.project.ws1.services.interfaces;

import ufp.esof.project.ws1.models.Consulta;

import java.util.Optional;
import java.util.Set;

public interface ConsultaServiceI {
    Optional<Consulta> findConsultaById(Long id);

    Set<Consulta> findAllConsultas();

    Set<Consulta> getConsultaByClienteId(Long id);

    Set<Consulta> getConsultaByMedicoId(Long id);

    Optional<Consulta> saveConsulta(Consulta consulta, Long medico_id, Long cliente_id);

    boolean delete(Long id);

    Optional<Consulta> getConsultaOfClienteById(Long cliente_id, Long consulta_id);

    Optional<Consulta> alterarHoraConsulta(Long consulta_id, String horario);

    Optional<Consulta> alterarConsultorioConsulta(Long consulta_id, String dia);

    Optional<Consulta> alterarDiaConsulta(Long consulta_id, String dia);

    Optional<Consulta> getConsultaOfMedicoById(Long medico_id, Long consulta_id);

    Set<Consulta> findConsultasByDay(String dia);
}
