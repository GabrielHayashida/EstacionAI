package org.estacionaai.controller;

import lombok.NoArgsConstructor;
import org.estacionaai.model.DTO.VeiculoDTO;
import org.estacionaai.model.VO.VeiculoVO;

import java.util.ArrayList;

@NoArgsConstructor
public class VeiculoController {
    private VeiculoDTO veiculoDAO;

    public VeiculoController(VeiculoDTO veiculoDAO) {
        this.veiculoDAO = veiculoDAO;
    }

    public ArrayList<VeiculoVO> getVeiculos() {
        return veiculoDAO.getVeiculos();
    }

    public VeiculoVO getVeiculoById(String placa) {
        return veiculoDAO.getVeiculosById(placa);
    }

    public boolean updateVeiculo(VeiculoVO veiculoVO) {
        return veiculoDAO.updateVeiculos(veiculoVO);
    }

    public boolean insertVeiculo(VeiculoVO veiculoVO) {
        return veiculoDAO.insertVeiculo(veiculoVO);
    }

    public boolean deleteVeiculo(String placa) {
        return veiculoDAO.deleteVeiculo(placa);
    }
}
