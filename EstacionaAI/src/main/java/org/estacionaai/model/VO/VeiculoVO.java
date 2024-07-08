package org.estacionaai.model.VO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VeiculoVO {
    private String placa;
    private String modelo;
    private String cor;
    private boolean vaga;

}
