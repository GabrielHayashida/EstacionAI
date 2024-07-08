package org.estacionaai.model.DAO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.estacionaai.controller.ConexaoBD;
import org.estacionaai.model.VO.VeiculoVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

            resultado.close();
            comando.close();

        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }

        return veiculos;

    }
    public static void main(String[] args) {
        // Criando uma instância do VeiculoDAO
        VeiculoDAO veiculoDAO = new VeiculoDAO();

        // Chamando o método getVeiculos para obter a lista de veículos
        ArrayList<VeiculoVO> veiculos = veiculoDAO.getVeiculos();

        // Exibindo os veículos obtidos no console
        System.out.println("Lista de Veículos:");
        for (VeiculoVO veiculo : veiculos) {
            System.out.println("Placa: " + veiculo.getPlaca());
            System.out.println("Modelo: " + veiculo.getModelo());
            System.out.println("Cor: " + veiculo.getCor());
            System.out.println("Vaga: " + (veiculo.isVaga() ? "Sim" : "Não"));
            System.out.println("----------------------");
        }
    }

}