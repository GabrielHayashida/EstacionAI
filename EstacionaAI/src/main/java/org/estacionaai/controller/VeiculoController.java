package org.estacionaai.controller;

import lombok.NoArgsConstructor;
import org.estacionaai.model.DAO.VeiculoDAO;
import org.estacionaai.model.VO.VeiculoVO;

import java.util.ArrayList;
@NoArgsConstructor

public class VeiculoController {
    private VeiculoDAO veiculoDAO;

    public VeiculoController(VeiculoDAO veiculoDAO) {
        this.veiculoDAO = veiculoDAO;
    }
    public ArrayList<VeiculoVO> getVeiculos() {
        return veiculoDAO.getVeiculos();

    }
}
