package org.estacionaai.controller;

import org.estacionaai.model.DTO.ReservaDTO;
import org.estacionaai.model.DTO.VagaDTO;
import org.estacionaai.model.VO.ReservaVO;
import org.estacionaai.model.VO.VagaVO;

import java.util.ArrayList;

public class ReservaController {
    private ReservaDTO reservaDTO;
    
    public ReservaController(ReservaDTO reservaDTO) {this.reservaDTO = reservaDTO;}

    public ArrayList<ReservaVO> getReservas(String pesquisa) {
        return reservaDTO.getReservas(pesquisa);
    }

    public ReservaVO getReservaById(int id){return reservaDTO.getReservaById(id);}

    public ArrayList<ReservaVO> getReservasByPlaca(String placa){return reservaDTO.getReservasByPlaca(placa);}

    public boolean updateReserva(ReservaVO reservaVO){return  reservaDTO.updateReserva(reservaVO);}

    public boolean insertReserva(ReservaVO reservaVO){return reservaDTO.insertReserva(reservaVO);}
}
