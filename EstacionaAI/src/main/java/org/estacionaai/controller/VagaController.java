package org.estacionaai.controller;

import lombok.NoArgsConstructor;
import org.estacionaai.model.DTO.VagaDTO;
import org.estacionaai.model.VO.VagaVO;

import java.util.ArrayList;

@NoArgsConstructor
public class VagaController {
    private VagaDTO vagaDTO;

    public VagaController(VagaDTO vagaDTO) {
        this.vagaDTO = vagaDTO;
    }

    public ArrayList<VagaVO> getVagas(String pesquisa) {
        return vagaDTO.getVagas(pesquisa);
    }

    public boolean updateVaga(VagaVO vagaVO) {
        return vagaDTO.updateVaga(vagaVO);
    }

    public boolean insertVaga(VagaVO vagaVO) {
        return vagaDTO.insertVaga(vagaVO);
    }

    public boolean deleteVaga(String id) {
        return vagaDTO.deleteVaga(Integer.parseInt(id));
    }
}
