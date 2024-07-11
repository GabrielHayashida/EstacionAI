package org.estacionaai.model.DTO;


import org.estacionaai.controller.ConexaoBD;
import org.estacionaai.model.VO.VeiculoVO;

import java.sql.*;
import java.util.ArrayList;

public class VeiculoDTO {

    public ArrayList<VeiculoVO> getVeiculos() {
        ArrayList<VeiculoVO> veiculos = new ArrayList<>();
        String comandoSQL = "SELECT * FROM veiculo";

        try {
            Statement comando = ConexaoBD.getConexaoBD().createStatement();
            ResultSet resultado = comando.executeQuery(comandoSQL);

            while (resultado.next()) {
                VeiculoVO veiculoVO = new VeiculoVO();

                veiculoVO.setPlaca(resultado.getString("placa"));
                veiculoVO.setModelo(resultado.getString("modelo"));
                veiculoVO.setCor(resultado.getString("cor"));
                veiculoVO.setAno(resultado.getInt("ano"));
                veiculoVO.setId_cliente(resultado.getInt("id_cliente"));


                veiculos.add(veiculoVO);
            }



        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }

        return veiculos;

    }
    public VeiculoVO getVeiculosById(String placa) {
        VeiculoVO veiculoVO = null;
        String comandoSQL = "SELECT * FROM veiculo WHERE veiculo.placa = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, placa);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                veiculoVO = new VeiculoVO();
                veiculoVO.setPlaca(resultado.getString("placa"));
                veiculoVO.setModelo(resultado.getString("modelo"));
                veiculoVO.setCor(resultado.getString("cor"));
                veiculoVO.setAno(resultado.getInt("ano"));
                veiculoVO.setId_cliente(resultado.getInt("id_cliente"));
            } else {
                System.err.println("Nenhum veículo encontrado com a placa: " + placa);
            }


        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return veiculoVO;
    }
    public boolean updateVeiculos(VeiculoVO veiculoVO){
        String comandoSQL = "UPDATE veiculos SET modelo = ?, cor = ?, ano = ? id_cliente = ?  WHERE placa = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, veiculoVO.getModelo());
            comando.setString(2, veiculoVO.getCor());
            comando.setInt(3, veiculoVO.getAno());
            comando.setInt(4, veiculoVO.getId_cliente());
            comando.setString(5, veiculoVO.getPlaca());

            int resultado = comando.executeUpdate();

            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar atualização SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertVeiculo(VeiculoVO veiculoVO) {
        String comandoSQL = "INSERT INTO veiculo (placa, modelo, cor, ano, id_cliente) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, veiculoVO.getPlaca());
            comando.setString(2, veiculoVO.getModelo());
            comando.setString(3, veiculoVO.getCor());
            comando.setInt(4, veiculoVO.getAno());
            comando.setInt(5, veiculoVO.getId_cliente());

            int resultado = comando.executeUpdate();

            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar inserção SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteVeiculo(String placa) {
        String comandoSQL = "DELETE FROM veiculo WHERE placa = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, placa);

            int resultado = comando.executeUpdate();

            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar deleção SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }





}