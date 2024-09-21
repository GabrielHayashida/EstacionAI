package org.estacionaai.model.DTO;

import org.estacionaai.controller.ConexaoBD;
import org.estacionaai.model.VO.VagaVO;

import java.sql.*;
import java.util.ArrayList;

public class VagaDTO {
    public ArrayList<VagaVO> getVagas(String pesquisa) {
        ArrayList<VagaVO> vagas = new ArrayList<>();
        String comandoSQL = "SELECT * FROM vaga WHERE descricao LIKE ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            // Preparar o parâmetro de pesquisa
            String pesquisaLike = "%" + pesquisa + "%";
            comando.setString(1, pesquisaLike);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                VagaVO vagaVO = new VagaVO();
                vagaVO.setId(resultado.getInt("id"));
                vagaVO.setDescricao(resultado.getString("descricao"));
                vagaVO.setOcupada(resultado.getBoolean("ocupada"));
                vagas.add(vagaVO);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }

        return vagas;
    }

    public boolean updateVaga(VagaVO vagaVO) {
        String comandoSQL = "UPDATE vaga SET descricao = ?, ocupada = ? WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, vagaVO.getDescricao());
            comando.setBoolean(2, vagaVO.isOcupada());
            comando.setInt(3, vagaVO.getId());

            int resultado = comando.executeUpdate();
            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar atualização SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertVaga(VagaVO vagaVO) {
        String comandoSQL = "INSERT INTO vaga (descricao, ocupada) VALUES (?, ?)";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, vagaVO.getDescricao());
            comando.setBoolean(2, vagaVO.isOcupada());

            int resultado = comando.executeUpdate();
            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar inserção SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteVaga(int id) {
        String comandoSQL = "DELETE FROM vaga WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setInt(1, id);

            int resultado = comando.executeUpdate();
            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar deleção SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
