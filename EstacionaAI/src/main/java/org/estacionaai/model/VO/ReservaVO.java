package org.estacionaai.model.VO;

import lombok.*;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservaVO {
    private int id;
    private VeiculoVO veiculo;
    private VagaVO vaga;
    private LocalDateTime data_entrada;
    private LocalDateTime data_saida;

}
