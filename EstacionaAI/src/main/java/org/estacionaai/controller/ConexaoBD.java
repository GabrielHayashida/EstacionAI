package org.estacionaai.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private static Connection conexao = null;

    private ConexaoBD() {}

    public static Connection getConexaoBD() {
        if (conexao == null || isConnectionClosed(conexao)) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexao = DriverManager.getConnection("jdbc:mysql://localhost/estacionai", "root", "");
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
}
