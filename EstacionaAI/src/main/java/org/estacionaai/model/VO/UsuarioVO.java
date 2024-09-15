package org.estacionaai.model.VO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UsuarioVO {
    private String login;
    private String senha;
    private int estacionamentoId;

    @Override
    public String toString() {
        return login;
    }
}
