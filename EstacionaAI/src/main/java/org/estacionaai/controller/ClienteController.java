package org.estacionaai.controller;

import org.estacionaai.model.DTO.ClienteDTO;
import org.estacionaai.model.VO.ClienteVO;

import java.util.List;

public class ClienteController {
    private final ClienteDTO clienteDTO;

    public ClienteController(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }

    // Método para listar clientes com base na pesquisa
    public List<ClienteVO> getClientes(String pesquisa) {
        return clienteDTO.getClientes(pesquisa);
    }

    // Método para buscar um cliente pelo ID
    public ClienteVO getClienteById(int id) {
        return clienteDTO.getClienteById(id);
    }

    // Método para atualizar um cliente
    public boolean updateCliente(ClienteVO clienteVO) {
        return clienteDTO.updateCliente(clienteVO);
    }

    // Método para inserir um novo cliente
    public boolean insertCliente(ClienteVO clienteVO) {
        return clienteDTO.insertCliente(clienteVO);
    }

    // Método para deletar um cliente
    public boolean deleteCliente(int id) {
        return clienteDTO.deleteCliente(id);
    }
}
