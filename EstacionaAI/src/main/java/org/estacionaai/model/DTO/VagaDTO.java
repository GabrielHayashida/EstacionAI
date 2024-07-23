package org.estacionaai.model.DTO;

import org.estacionaai.controller.ConexaoBD;
import org.estacionaai.model.VO.VagaVO;
import org.estacionaai.model.VO.VeiculoVO;

import java.sql.*;
import java.util.ArrayList;

public class VagaDTO {
    public ArrayList<VagaVO> getVagas(String pesquisa) {
        ArrayList<VagaVO> vagas = new ArrayList<>();
        String comandoSQL = "SELECT * FROM vaga WHERE numero LIKE ? OR setor LIKE ? OR tipo LIKE ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            // Preparar o parâmetro de pesquisa
            String pesquisaLike = "%" + pesquisa + "%";
            comando.setString(1, pesquisaLike);
            comando.setString(2, pesquisaLike);
            comando.setString(3, pesquisaLike);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                VagaVO vagaVO = new VagaVO();
                vagaVO.setId(resultado.getInt("id"));
                vagaVO.setNumero(resultado.getInt("numero"));
                vagaVO.setSetor(resultado.getString("setor"));
                vagaVO.setTipo(resultado.getString("tipo"));
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
        String comandoSQL = "UPDATE vaga SET numero = ?, setor = ?, tipo = ?, ocupada = ? WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setInt(1, vagaVO.getNumero());
            comando.setString(2, vagaVO.getSetor());
            comando.setString(3, vagaVO.getTipo());
            comando.setBoolean(4, vagaVO.getOcupada());
            comando.setInt(5, vagaVO.getId());

            int resultado = comando.executeUpdate();
            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar atualização SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertVaga(VagaVO vagaVO) {
        String comandoSQL = "INSERT INTO vaga (numero, setor, tipo, ocupada) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setInt(1, vagaVO.getNumero());
            comando.setString(2, vagaVO.getSetor());
            comando.setString(3, vagaVO.getTipo());
            comando.setBoolean(4, vagaVO.getOcupada());

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
