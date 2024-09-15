package org.estacionaai.model.VO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ReservaVO {
    private int id;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private BigDecimal valorTotal;
    private int clienteId;
    private String veiculoPlaca;
    private int vagaId;
    private int estacionamentoId;

    @Override
    public String toString() {
        return "Reserva #" + id + " - Cliente ID: " + clienteId;
    }
}
