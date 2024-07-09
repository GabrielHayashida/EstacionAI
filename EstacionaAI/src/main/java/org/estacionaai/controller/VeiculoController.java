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
    public VeiculoVO getVeiculosById(String placa) {
        return veiculoDAO.getVeiculosById(placa);
    }
    public boolean updateVeiculos(VeiculoVO veiculoVO){
        return veiculoDAO.updateVeiculos(veiculoVO);
    }
    public boolean insertVeiculo(VeiculoVO veiculoVO){
        return veiculoDAO.insertVeiculo(veiculoVO);
    }
}
