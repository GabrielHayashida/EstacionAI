package org.estacionaai.model.DTO;

import org.estacionaai.controller.ConexaoBD;
import org.estacionaai.model.VO.VagaVO;
import org.estacionaai.model.VO.VeiculoVO;

import java.sql.*;
import java.util.ArrayList;

public class VagaDTO {
    public ArrayList<VagaVO> getVagas() {
        ArrayList<VagaVO> vagas = new ArrayList<>();
        String comandoSQL = "SELECT * FROM vaga";

        try {
            Statement comando = ConexaoBD.getConexaoBD().createStatement();
            ResultSet resultado = comando.executeQuery(comandoSQL);

            while (resultado.next()) {
                VagaVO vagaVO = new VagaVO();


                vagaVO.setId(resultado.getInt("id"));
                vagaVO.setNumero(resultado.getInt("numero"));
                vagaVO.setSetor(resultado.getString("setor"));
                vagaVO.setTipo(resultado.getString("tipo"));



                vagas.add(vagaVO);
            }



        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }

        return vagas;

    }
    public boolean updateVagas(VagaVO vagaVO){
        String comandoSQL = "UPDATE vaga SET  numero = ?, setor = ?, tipo = ?  WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setInt(1, vagaVO.getNumero());
            comando.setString(2, vagaVO.getSetor());
            comando.setString(3, vagaVO.getTipo());
            comando.setInt(4, vagaVO.getId());


            int resultado = comando.executeUpdate();

            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar atualização SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertVaga(VagaVO vagaVO) {
        String comandoSQL = "INSERT INTO vaga (id, numero, setor, tipo) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setInt(1, vagaVO.getId());
            comando.setInt(2, vagaVO.getNumero());
            comando.setString(3, vagaVO.getSetor());
            comando.setString(4, vagaVO.getTipo());


            int resultado = comando.executeUpdate();

            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar inserção SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteVaga(String id) {
        String comandoSQL = "DELETE FROM vaga WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, id);

            int resultado = comando.executeUpdate();

            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar deleção SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }



}
