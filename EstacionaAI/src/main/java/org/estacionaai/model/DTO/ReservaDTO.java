package org.estacionaai.model.DTO;

import org.estacionaai.controller.ConexaoBD;
import org.estacionaai.model.VO.ReservaVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservaDTO {

    public ArrayList<ReservaVO> getReservas(String pesquisa) {
        ArrayList<ReservaVO> reservas = new ArrayList<>();
        String comandoSQL = "SELECT * FROM reserva WHERE placa_veiculo LIKE ? OR id_vaga LIKE ? OR data_entrada LIKE ? OR data_saida LIKE ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            // Preparar o parâmetro de pesquisa
            String pesquisaLike = "%" + pesquisa + "%";
            comando.setString(1, pesquisaLike);
            comando.setString(2, pesquisaLike);
            comando.setString(3, pesquisaLike);
            comando.setString(4, pesquisaLike);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                ReservaVO reservaVO = new ReservaVO();
                reservaVO.setId(resultado.getInt("id"));
                reservaVO.setVagaId(resultado.getInt("id_vaga"));
                reservaVO.setVeiculoPlaca(resultado.getString("placa_veiculo"));
                reservaVO.setDataHoraEntrada(resultado.getTimestamp("data_entrada").toLocalDateTime());

                if (resultado.getTimestamp("data_saida") == null) {
                    reservaVO.setDataHoraSaida(null);
                } else {
                    reservaVO.setDataHoraSaida(resultado.getTimestamp("data_saida").toLocalDateTime());
                }

                reservas.add(reservaVO);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }

        return reservas;
    }

    public ReservaVO getReservaById(int id) {
        ReservaVO reservaVO = null;
        String comandoSQL = "SELECT * FROM reserva WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                reservaVO = new ReservaVO();
                reservaVO.setId(resultado.getInt("id"));
                reservaVO.setVagaId(resultado.getInt("id_vaga"));
                reservaVO.setVeiculoPlaca(resultado.getString("placa_veiculo"));
                reservaVO.setDataHoraEntrada(resultado.getTimestamp("data_entrada").toLocalDateTime());
                reservaVO.setDataHoraSaida(resultado.getTimestamp("data_saida").toLocalDateTime());
            } else {
                System.err.println("Nenhuma reserva encontrada com o ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }

        return reservaVO;
    }

    public ArrayList<ReservaVO> getReservasByPlaca(String placa) {
        ArrayList<ReservaVO> reservas = new ArrayList<>();
        String comandoSQL = "SELECT * FROM reserva WHERE placa_veiculo = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, placa);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                ReservaVO reservaVO = new ReservaVO();
                reservaVO.setId(resultado.getInt("id"));
                reservaVO.setVagaId(resultado.getInt("id_vaga"));
                reservaVO.setVeiculoPlaca(resultado.getString("placa_veiculo"));
                reservaVO.setDataHoraEntrada(resultado.getTimestamp("data_entrada").toLocalDateTime());
                reservaVO.setDataHoraSaida(resultado.getTimestamp("data_saida").toLocalDateTime());

                reservas.add(reservaVO);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }

        return reservas;
    }

    public boolean updateReserva(ReservaVO reservaVO) {
        String comandoSQL = "UPDATE reserva SET placa_veiculo = ?, id_vaga = ?, data_entrada = ?, data_saida = ? WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, reservaVO.getVeiculoPlaca());
            comando.setInt(2, reservaVO.getVagaId());
            comando.setString(3, reservaVO.getDataHoraEntrada().toString());
            comando.setString(4, reservaVO.getDataHoraSaida() != null ? reservaVO.getDataHoraSaida().toString() : null);
            comando.setInt(5, reservaVO.getId());

            int resultado = comando.executeUpdate();

            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar atualização SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertReserva(ReservaVO reservaVO) {
        String comandoSQL = "INSERT INTO reserva (id, placa_veiculo, id_vaga, data_entrada, data_saida) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setInt(1, reservaVO.getId());
            comando.setString(2, reservaVO.getVeiculoPlaca());
            comando.setInt(3, reservaVO.getVagaId());
            comando.setString(4, reservaVO.getDataHoraEntrada().toString());
            comando.setString(5, reservaVO.getDataHoraSaida() != null ? reservaVO.getDataHoraSaida().toString() : null);

            int resultado = comando.executeUpdate();

            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar inserção SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
