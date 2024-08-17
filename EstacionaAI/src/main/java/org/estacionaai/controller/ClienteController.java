package org.estacionaai.controller;


import org.estacionaai.model.DTO.ClienteDTO;
import org.estacionaai.model.VO.ClienteVO;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

public class ClienteController {
    private ClienteDTO clienteDTO;

    public ClienteController(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }

    public ArrayList<ClienteVO> listarClientes(String pesquisa) {
        return clienteDTO.getClientes(pesquisa);
    }

    public ClienteVO buscarClientePorId(int id) {
        return clienteDTO.getClienteById(id);
    }

    public boolean atualizarCliente(ClienteVO clienteVO) {
        return clienteDTO.updateCliente(clienteVO);
    }

    public boolean inserirCliente(ClienteVO clienteVO) {
        return clienteDTO.insertCliente(clienteVO);
    }

    public boolean deletarCliente(int id) {
        return clienteDTO.deleteCliente(id);
    }
    public boolean verificarCredenciais(String email, String senha) {
        // Recupera o hash armazenado para o email fornecido
        String hashArmazenado = clienteDTO.getHashSenhaPorEmail(email);

        // Imprime informações para depuração
        System.out.println("Hash armazenado: " + hashArmazenado);
        System.out.println("Senha fornecida (para depuração): '" + senha + "'");

        // Verifica a senha fornecida contra o hash armazenado
        boolean senhasCorretas = BCrypt.checkpw(senha, hashArmazenado);

        // Imprime resultado da verificação
        System.out.println("Senha verificada: " + senhasCorretas);

        return senhasCorretas;
    }

}
