package ufp.esof.project.ws1.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public class Pessoa extends BaseModel {

    private String name;
    private String address;
    private String tlm;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate datanascimento;
    private String cc;

    public Pessoa() {

    }

    public Pessoa(String name, String address, LocalDate datanascimento, String cc, String tlm, String email) {
        this.name = name;
        this.address = address;
        this.datanascimento = datanascimento;
        this.cc = cc;
        this.tlm = tlm;
        this.email = email;
    }


}
