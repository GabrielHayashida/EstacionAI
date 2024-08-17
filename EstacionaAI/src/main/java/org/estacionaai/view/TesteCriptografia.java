package org.estacionaai.view;

import org.estacionaai.utils.CriptografiaSenha;

public class TesteCriptografia {
    public static void main(String[] args) {
        // Senha a ser testada
        String senhaOriginal = "123";

        // Cria o hash da senha
        String hash = CriptografiaSenha.criptografar(senhaOriginal);
        System.out.println("Hash gerado: " + hash);

        // Verifica o hash gerado com a senha fornecida
        boolean verifica = CriptografiaSenha.verificarSenha(senhaOriginal, hash);
        System.out.println("Senha verificada: " + verifica);

        // Testa com uma senha incorreta
        boolean verificaIncorreta = CriptografiaSenha.verificarSenha("senhaErrada", hash);
        System.out.println("Senha incorreta verificada: " + verificaIncorreta);
    }
}
