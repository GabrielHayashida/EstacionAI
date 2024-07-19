package org.estacionaai.model.DTO;

import org.estacionaai.controller.ConexaoBD;
import org.estacionaai.model.VO.ClienteVO;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDTO {

    public ArrayList<ClienteVO> getClientes(String pesquisa) {
        ArrayList<ClienteVO> clientes = new ArrayList<>();
        String comandoSQL = "SELECT * FROM cliente where nome like ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando =  conexao.prepareStatement(comandoSQL)){

            comando.setString(1, "%" + pesquisa + "%");
            ResultSet resultado = comando.executeQuery();


            while (resultado.next()) {
                ClienteVO clienteVO = new ClienteVO();

                clienteVO.setId(resultado.getInt("id"));
                clienteVO.setNome(resultado.getString("nome"));
                clienteVO.setTelefone(resultado.getString("telefone"));
                clienteVO.setEmail(resultado.getString("email"));
                clienteVO.setSenha(resultado.getString("senha"));
                clienteVO.setAdmin(resultado.getBoolean("admin"));
                clienteVO.setEndereco(resultado.getString("endereco"));

                clientes.add(clienteVO);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }

        return clientes;
    }

    public ClienteVO getClienteById(int id) {
        ClienteVO clienteVO = null;
        String comandoSQL = "SELECT * FROM cliente WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                clienteVO = new ClienteVO();
                clienteVO.setId(resultado.getInt("id"));
                clienteVO.setNome(resultado.getString("nome"));
                clienteVO.setTelefone(resultado.getString("telefone"));
                clienteVO.setEmail(resultado.getString("email"));
                clienteVO.setSenha(resultado.getString("senha"));
                clienteVO.setAdmin(resultado.getBoolean("admin"));
                clienteVO.setEndereco(resultado.getString("endereco"));
            } else {
                System.err.println("Nenhum cliente encontrado com o ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }

        return clienteVO;
    }

    public boolean updateCliente(ClienteVO clienteVO) {
        String comandoSQL = "UPDATE cliente SET nome = ?, telefone = ?, email = ?, senha = ?, admin = ?, endereco = ? WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, clienteVO.getNome());
            comando.setString(2, clienteVO.getTelefone());
            comando.setString(3, clienteVO.getEmail());
            comando.setString(4, clienteVO.getSenha());
            comando.setBoolean(5, clienteVO.isAdmin());
            comando.setString(6, clienteVO.getEndereco());
            comando.setInt(7, clienteVO.getId());

            int resultado = comando.executeUpdate();
            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar atualização SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertCliente(ClienteVO clienteVO) {
        String comandoSQL = "INSERT INTO cliente (nome, telefone, email, senha, admin, endereco) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, clienteVO.getNome());
            comando.setString(2, clienteVO.getTelefone());
            comando.setString(3, clienteVO.getEmail());
            comando.setString(4, clienteVO.getSenha());
            comando.setBoolean(5, clienteVO.isAdmin());
            comando.setString(6, clienteVO.getEndereco());

            int resultado = comando.executeUpdate();
            return resultado != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar inserção SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCliente(int id) {
        String comandoSQL = "DELETE FROM cliente WHERE id = ?";

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
