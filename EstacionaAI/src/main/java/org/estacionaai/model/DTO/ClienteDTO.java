package org.estacionaai.model.DTO;

import org.estacionaai.controller.ConexaoBD;
import org.estacionaai.model.VO.ClienteVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {


    public List<ClienteVO> getClientes(String pesquisa) {
        List<ClienteVO> clientes = new ArrayList<>();
        String comandoSQL = "SELECT * FROM cliente WHERE nome LIKE ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setString(1, "%" + pesquisa + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                clientes.add(criarClienteVO(resultado));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
        }

        return clientes;
    }

    // Método para obter um cliente pelo ID
    public ClienteVO getClienteById(int id) {
        ClienteVO clienteVO = null;
        String comandoSQL = "SELECT * FROM cliente WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                clienteVO = criarClienteVO(resultado);
            } else {
                System.err.println("Nenhum cliente encontrado com o ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
        }

        return clienteVO;
    }


    public boolean updateCliente(ClienteVO clienteVO) {
        String comandoSQL = "UPDATE cliente SET nome = ?, telefone = ?, email = ?, admin = ?, endereco = ?, data_nascimento = ? WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            setClienteParameters(comando, clienteVO);
            comando.setInt(7, clienteVO.getId());

            return comando.executeUpdate() != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar atualização SQL: " + e.getMessage());
            return false;
        }
    }

    // Método para inserir um cliente
    public boolean insertCliente(ClienteVO clienteVO) {
        String comandoSQL = "INSERT INTO cliente (nome, telefone, email, admin, endereco, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            setClienteParameters(comando, clienteVO);

            return comando.executeUpdate() != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar inserção SQL: " + e.getMessage());
            return false;
        }
    }


    public boolean deleteCliente(int id) {
        String comandoSQL = "DELETE FROM cliente WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConexaoBD();
             PreparedStatement comando = conexao.prepareStatement(comandoSQL)) {

            comando.setInt(1, id);
            return comando.executeUpdate() != 0;

        } catch (SQLException e) {
            System.err.println("Erro ao executar deleção SQL: " + e.getMessage());
            return false;
        }
    }


    private ClienteVO criarClienteVO(ResultSet resultado) throws SQLException {
        ClienteVO clienteVO = new ClienteVO();
        clienteVO.setId(resultado.getInt("id"));
        clienteVO.setNome(resultado.getString("nome"));
        clienteVO.setTelefone(resultado.getString("telefone"));
        clienteVO.setEmail(resultado.getString("email"));
        clienteVO.setEndereco(resultado.getString("endereco"));
        clienteVO.setDataNascimento(resultado.getDate("data_nascimento"));
        return clienteVO;
    }


    private void setClienteParameters(PreparedStatement comando, ClienteVO clienteVO) throws SQLException {
        comando.setString(1, clienteVO.getNome());
        comando.setString(2, clienteVO.getTelefone());
        comando.setString(3, clienteVO.getEmail());
        comando.setString(4, clienteVO.getEndereco());
        comando.setDate(5, new java.sql.Date(clienteVO.getDataNascimento().getTime()));
    }
}
