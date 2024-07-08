package org.estacionaai.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private static Connection conexao = null;

    private ConexaoBD() {}

    public static Connection getConexaoBD() {
        if (conexao == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexao = DriverManager.getConnection("jdbc:mysql://localhost/estacionai", "root", "");
            } catch (ClassNotFoundException e) {
                System.err.println("Erro ao carregar o driver, verifique o arquivo hsqldb.jar no classpath");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Erro ao realizar conexao com o banco, verifique a URL de conexão");
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

    public static void main(String[] args) {

        Connection conn = ConexaoBD.getConexaoBD();
        if (conn != null) {
            System.out.println("Conexão estabelecida com sucesso!");
        } else {
            System.err.println("Falha ao estabelecer conexão.");
        }

        // Fecha a conexão com o banco de dados
        ConexaoBD.closeConexaoBD();
    }
}
