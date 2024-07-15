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
    private String placa_veiculo;
    private int id_vaga;
    private LocalDateTime data_entrada;
    private LocalDateTime data_saida;

}
