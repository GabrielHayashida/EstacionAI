package org.estacionaai.controller;


import org.estacionaai.model.DTO.ClienteDTO;
import org.estacionaai.model.VO.ClienteVO;

import java.util.ArrayList;

public class ClienteController {
    private ClienteDTO clienteDTO;

    public ClienteController() {
        this.clienteDTO = new ClienteDTO();
    }

    public ArrayList<ClienteVO> listarClientes() {
        return clienteDTO.getClientes();
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

}
