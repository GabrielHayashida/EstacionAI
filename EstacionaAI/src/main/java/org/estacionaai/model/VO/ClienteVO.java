package org.estacionaai.model.VO;

import lombok.*;

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
    private String senha;
    private boolean admin;
    private String endereco;

}
