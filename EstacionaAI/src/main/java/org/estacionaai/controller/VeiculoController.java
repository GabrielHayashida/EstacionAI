package org.estacionaai.controller;

import lombok.NoArgsConstructor;
import org.estacionaai.model.DTO.VeiculoDTO;
import org.estacionaai.model.VO.VeiculoVO;

import java.util.List;

@NoArgsConstructor
public class VeiculoController {
    private VeiculoDTO veiculoDTO;

    public VeiculoController(VeiculoDTO veiculoDTO) {
        this.veiculoDTO = veiculoDTO;
    }

    public List<VeiculoVO> getVeiculos(String pesquisa) {
        return veiculoDTO.getVeiculos(pesquisa);
    }

    public VeiculoVO getVeiculoById(String placa) {
        return veiculoDTO.getVeiculoById(placa);
    }

    public boolean updateVeiculo(VeiculoVO veiculoVO) {
        return veiculoDTO.updateVeiculo(veiculoVO);
    }

    public boolean insertVeiculo(VeiculoVO veiculoVO) {
        return veiculoDTO.insertVeiculo(veiculoVO);
    }

    public boolean deleteVeiculo(String placa) {
        return veiculoDTO.deleteVeiculo(placa);
    }
}
