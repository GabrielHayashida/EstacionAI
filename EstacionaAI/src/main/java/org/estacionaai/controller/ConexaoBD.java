package org.estacionaai.controller;

import java.sql.*;
import java.time.LocalDate;

public class ConexaoBD {

    private static Connection conexao = null;

    private ConexaoBD() {}

    public static Connection getConexaoBD() {
        if (conexao == null || isConnectionClosed(conexao)) {
            try {
                Class.forName("org.hsqldb.jdbc.JDBCDriver");
                conexao = DriverManager.getConnection("jdbc:hsqldb:file:E:/Fatec/EstacionaAI/EstacionaAI/src/main/java/org/estacionaai/database/estacionaidb/estacionai;", "SA", "");
            } catch (ClassNotFoundException e) {
                System.err.println("Erro ao carregar o driver, verifique o arquivo hsqldb.jar no classpath");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Erro ao realizar conexão com o banco, verifique a URL de conexão");
                e.printStackTrace();
            }
        }
        return conexao;
    }

    public static void closeConexaoBD() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao realizar fechamento da conexão com o banco");
                e.printStackTrace();
            }
        }
    }

    private static boolean isConnectionClosed(Connection conn) {
        try {
            return conn == null || conn.isClosed();
        } catch (SQLException e) {
            System.err.println("Erro ao verificar se a conexão está fechada");
            e.printStackTrace();
            return true;
        }
    }

    public static void insertCliente(String nome, String dataNascimento, String endereco, String email, String telefone) {
        String sql = "INSERT INTO \"CLIENTE\" (nome, data_nascimento, endereco, email, telefone) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = getConexaoBD();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, dataNascimento);
            pstmt.setString(3, endereco);
            pstmt.setString(4, email);
            pstmt.setString(5, telefone);
            pstmt.executeUpdate();

            System.out.println("Cliente inserido com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    public static void listarClientes() {
        String sql = "SELECT * FROM \"Cliente\";";

        try (Connection conn = getConexaoBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Obtendo os dados de cada coluna
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String dataNascimento = rs.getString("data_nascimento");
                String endereco = rs.getString("endereco");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");

                // Imprimindo os dados no console
                System.out.println("ID: " + id + ", Nome: " + nome + ", Data de Nascimento: " + dataNascimento +
                        ", Endereço: " + endereco + ", Email: " + email + ", Telefone: " + telefone);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao consultar clientes: " + e.getMessage());
        }
    }

    public static void main(String[] args) {


        // Listar os clientes após a inserção
        listarClientes();

        closeConexaoBD(); // Fecha a conexão ao final
    }


}
