package org.estacionaai.controller;

import org.estacionaai.model.DTO.ClienteDTO;
import org.estacionaai.model.VO.ClienteVO;

import java.util.ArrayList;

public class ClienteController {
    private ClienteDTO clienteDTO;

    public ClienteController() {
        this.clienteDTO = new ClienteDTO();
    }

    public ArrayList<ClienteVO> getTodosClientes() {
        return clienteDTO.getClientes();
    }

    public ClienteVO getClientePorId(int id) {
        return clienteDTO.getClienteById(id);
    }

    public boolean adicionarCliente(ClienteVO clienteVO) {
        return clienteDTO.insertCliente(clienteVO);
    }

    public boolean atualizarCliente(ClienteVO clienteVO) {
        return clienteDTO.updateCliente(clienteVO);
    }

    public boolean removerCliente(int id) {
        return clienteDTO.deleteCliente(id);
    }
}
