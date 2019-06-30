package ufp.esof.project.ws1_1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufp.esof.project.ws1_1.models.Cliente;
import ufp.esof.project.ws1_1.models.Consulta;
import ufp.esof.project.ws1_1.models.Medico;
import ufp.esof.project.ws1_1.repositories.ClienteRepo;
import ufp.esof.project.ws1_1.repositories.ConsultaRepo;
import ufp.esof.project.ws1_1.repositories.MedicoRepo;
import ufp.esof.project.ws1_1.services.interfaces.ConsultaServiceI;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ConsultaService implements ConsultaServiceI {

    @Autowired
    private ClienteRepo clienterepo;
    @Autowired
    private MedicoRepo medicorepo;
    @Autowired
    private ConsultaRepo consultarepo;


    @Override
    public Optional<Consulta> findConsultaById(Long id) {
        return Optional.empty();
    }

    @Override
    public Set<Consulta> findAllConsultas() {

        Set<Consulta> consultas=new HashSet<> ();
        for(Consulta consulta:this.consultarepo.findAll()){
            consultas.add(consulta);
        }
        return Collections.unmodifiableSet(consultas);
    }

    @Override
    public Set<Consulta> getConsultaByClienteId(Long id) {

        Set<Consulta> consultas=new HashSet<>();
        for(Consulta consulta:findAllConsultas()){
            if (consulta.getCliente().getId().equals(id)){
                consultas.add(consulta);
            }
        }
        return Collections.unmodifiableSet(consultas);
    }

    @Override
    public Set<Consulta> getConsultaByMedicoId(Long id) {

        Set<Consulta> consultas=new HashSet<>();
        for(Consulta consulta:findAllConsultas()){
            if (consulta.getMedico().getId().equals(id)){
                consultas.add(consulta);
            }
        }
        return Collections.unmodifiableSet(consultas);
    }

    @Override
    public Optional<Consulta> saveConsulta(Consulta consulta, Long medico_id, Long cliente_id) {
        Optional<Medico> medicoOptional=this.medicorepo.findById(medico_id);
        Optional<Cliente> clienteOptional=this.clienterepo.findById(cliente_id);
        if(medicoOptional.isPresent() && clienteOptional.isPresent()){
            Medico medico=medicoOptional.get();
            Cliente cliente=clienteOptional.get();
            consulta.setMedico(medico);
            consulta.setCliente(cliente);
            consulta.setEspecialidade(medico.getEspecialidade());

            medico.addConsulta(consulta);
            medicorepo.save(medico);
            clienterepo.save(cliente);
            return consultarepo.findById(consulta.getId());
        }
        return Optional.empty();

    }



}
