package org.estacionaai.model.VO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VagaVO {
    private int id;
    private int numero;
    private String setor;
    private String tipo;
    private Boolean ocupada;

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
