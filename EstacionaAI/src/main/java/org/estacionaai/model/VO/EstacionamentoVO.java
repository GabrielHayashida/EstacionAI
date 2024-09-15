package org.estacionaai.model.VO;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class EstacionamentoVO {
    private int id;
    private String nome;
    private BigDecimal valorNormal;
    private BigDecimal valorFixo;
    private String email;

    @Override
    public String toString() {
        return nome;
    }
}
