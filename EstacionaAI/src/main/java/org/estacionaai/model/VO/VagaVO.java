package org.estacionaai.model.VO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class VagaVO {
    private int id;
    private String descricao;
    private boolean ocupada;

    @Override
    public String toString() {
        return descricao;
    }
}
