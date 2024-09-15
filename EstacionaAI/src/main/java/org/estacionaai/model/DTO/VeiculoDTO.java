package org.estacionaai.model.DTO;

import org.estacionaai.controller.ConexaoBD;
import org.estacionaai.model.VO.VeiculoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDTO {

    public List<VeiculoVO> getVeiculos(String pesquisa) {
        List<VeiculoVO> veiculos = new ArrayList<>();
        String comandoSQL = "SELECT * FROM veiculo WHERE placa LIKE ? OR modelo LIKE ? OR cor LIKE ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            // Preparar o parâmetro de pesquisa
            String pesquisaLike = "%" + pesquisa + "%";
            comando.setString(1, pesquisaLike);
            comando.setString(2, pesquisaLike);
            comando.setString(3, pesquisaLike);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                VeiculoVO veiculoVO = new VeiculoVO();
                veiculoVO.setPlaca(resultado.getString("placa"));
                veiculoVO.setModelo(resultado.getString("modelo"));
                veiculoVO.setCor(resultado.getString("cor"));
                veiculoVO.setAcesso(resultado.getBoolean("acesso")); // Adicionei acesso aqui
                veiculoVO.setClienteId(resultado.getInt("id_cliente")); // Mudando para clienteId
                veiculos.add(veiculoVO);
            }

        } catch (SQLException e) {
            // Use um logger em vez de System.err
            System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }

        return veiculos;
    }

    public VeiculoVO getVeiculoById(String placa) {
        VeiculoVO veiculoVO = null;
        String comandoSQL = "SELECT * FROM veiculo WHERE placa = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, placa);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                veiculoVO = new VeiculoVO();
                veiculoVO.setPlaca(resultado.getString("placa"));
                veiculoVO.setModelo(resultado.getString("modelo"));
                veiculoVO.setCor(resultado.getString("cor"));
                veiculoVO.setAcesso(resultado.getBoolean("acesso")); // Adicionei acesso aqui
                veiculoVO.setClienteId(resultado.getInt("id_cliente")); // Mudando para clienteId
            } else {
                System.err.println("Nenhum veículo encontrado com a placa: " + placa);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }

        return veiculoVO;
    }

    public boolean updateVeiculo(VeiculoVO veiculoVO) {
        String comandoSQL = "UPDATE veiculo SET modelo = ?, cor = ?, acesso = ?, id_cliente = ? WHERE placa = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, veiculoVO.getModelo());
            comando.setString(2, veiculoVO.getCor());
            comando.setBoolean(3, veiculoVO.isAcesso()); // Mudando para acesso
            comando.setInt(4, veiculoVO.getClienteId()); // Mudando para clienteId
            comando.setString(5, veiculoVO.getPlaca());

            return comando.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar atualização SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertVeiculo(VeiculoVO veiculoVO) {
        String comandoSQL = "INSERT INTO veiculo (placa, modelo, cor, acesso, id_cliente) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, veiculoVO.getPlaca());
            comando.setString(2, veiculoVO.getModelo());
            comando.setString(3, veiculoVO.getCor());
            comando.setBoolean(4, veiculoVO.isAcesso()); // Mudando para acesso
            comando.setInt(5, veiculoVO.getClienteId()); // Mudando para clienteId

            return comando.executeUpdate() > 0;

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
            return comando.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar deleção SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
