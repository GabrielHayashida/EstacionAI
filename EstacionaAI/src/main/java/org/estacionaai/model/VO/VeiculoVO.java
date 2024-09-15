package org.estacionaai.model.VO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class VeiculoVO {
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private boolean acesso;
    private int clienteId;

    @Override
    public String toString() {
        return marca + " " + modelo + " (" + placa + ")";
    }
}
