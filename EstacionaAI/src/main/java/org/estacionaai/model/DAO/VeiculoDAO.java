package org.estacionaai.model.DAO;


import org.estacionaai.controller.ConexaoBD;
import org.estacionaai.model.VO.VeiculoVO;

import java.sql.*;
import java.util.ArrayList;

public class VeiculoDAO {

    public ArrayList<VeiculoVO> getVeiculos() {
        ArrayList<VeiculoVO> veiculos = new ArrayList<>();
        String comandoSQL = "SELECT * FROM veiculos";

        try {
            Statement comando = ConexaoBD.getConexaoBD().createStatement();
            ResultSet resultado = comando.executeQuery(comandoSQL);

            while (resultado.next()) {
                VeiculoVO veiculoVO = new VeiculoVO();

                veiculoVO.setPlaca(resultado.getString("placa"));
                veiculoVO.setModelo(resultado.getString("modelo"));
                veiculoVO.setCor(resultado.getString("cor"));
                veiculoVO.setVaga(resultado.getBoolean("vaga"));

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
        String comandoSQL = "SELECT * FROM veiculos WHERE veiculos.placa = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, placa);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                veiculoVO = new VeiculoVO();
                veiculoVO.setPlaca(resultado.getString("placa"));
                veiculoVO.setModelo(resultado.getString("modelo"));
                veiculoVO.setCor(resultado.getString("cor"));
                veiculoVO.setVaga(resultado.getBoolean("vaga"));
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
        String comandoSQL = "UPDATE veiculos SET modelo = ?, cor = ?, vaga = ? WHERE placa = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, veiculoVO.getModelo());
            comando.setString(2, veiculoVO.getCor());
            comando.setBoolean(3, veiculoVO.isVaga());
            comando.setString(4, veiculoVO.getPlaca());

            int resultado = comando.executeUpdate();

            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar atualização SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertVeiculo(VeiculoVO veiculoVO){

        String comandoSQL = "INSERT INTO veiculos (placa, modelo, cor, vaga) VALUES ('"
                + veiculoVO.getPlaca() + "', '"
                + veiculoVO.getModelo() + "', '"
                + veiculoVO.getCor() + "', "
                + veiculoVO.isVaga() + ");";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             Statement comando = conexao.createStatement()) {

            int resultado = comando.executeUpdate(comandoSQL);

            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar inserção SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }




}