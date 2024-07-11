package org.estacionaai.controller;

import lombok.NoArgsConstructor;
import org.estacionaai.model.DTO.VagaDTO;
import org.estacionaai.model.VO.VagaVO;

import java.util.ArrayList;

@NoArgsConstructor
public class VagaController {
    private VagaDTO vagaDAO;

    public VagaController(VagaDTO vagaDAO) {
        this.vagaDAO = vagaDAO;
    }

    public ArrayList<VagaVO> getVaga() {
        return vagaDAO.getVagas();
    }

    public boolean updateVaga(VagaVO vagaVO) {
        return vagaDAO.updateVagas(vagaVO);
    }

    public boolean insertVaga(VagaVO vagaVO) {
        return vagaDAO.insertVaga(vagaVO);
    }

    public boolean deleteVaga(String id) {
        return vagaDAO.deleteVaga(id);
    }
}
