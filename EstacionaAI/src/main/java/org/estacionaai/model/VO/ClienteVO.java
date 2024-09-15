package org.estacionaai.model.VO;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteVO {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private Date dataNascimento;
    @Override
    public String toString() {
        return nome;
    }
}
