package org.estacionaai.utils;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CriptografiaSenha {

    private static final String CHAVE = "1234567890123456"; // Chave de 16 bytes para AES-128

    // Método para criptografar a senha
    public static String criptografar(String senha) {
        try {
            SecretKey chaveSecreta = new SecretKeySpec(CHAVE.getBytes(), "AES");
            Cipher cifra = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cifra.init(Cipher.ENCRYPT_MODE, chaveSecreta);

            byte[] textoCriptografado = cifra.doFinal(senha.getBytes());

            return Base64.getEncoder().encodeToString(textoCriptografado);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para descriptografar a senha
    public static String descriptografar(String senhaCriptografada) {
        try {
            SecretKey chaveSecreta = new SecretKeySpec(CHAVE.getBytes(), "AES");
            Cipher cifra = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cifra.init(Cipher.DECRYPT_MODE, chaveSecreta);

            byte[] textoCriptografado = Base64.getDecoder().decode(senhaCriptografada);
            byte[] textoDescriptografado = cifra.doFinal(textoCriptografado);

            return new String(textoDescriptografado);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}