package com.controller.security;

import com.orm.Usuario;
import jakarta.ws.rs.BadRequestException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class HashingController {

    public static String hashingSenha(String password, String salt){
        return PBKDF2(password, salt);
    }

    private static String PBKDF2(String password, String salt){

        if (password == null || password.isBlank() || salt == null){
            throw new BadRequestException();
        }

        try {
            final KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 512);
            final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            return Hex.encodeHexString(factory.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public static boolean isEquals(Usuario usuario, String password){

        if(password == null || usuario.getDsSenha() == null){
            return false;
        }

        return usuario.getDsSenha().equals(PBKDF2(password, usuario.getDsSalt()));
    }

    public static boolean isEqualsTokenRefector(Usuario usuario, String token){

        if(token == null || usuario.getDsSenha() == null){
            return false;
        }

        return usuario.getDsTokenRecuperacao().equals(PBKDF2(token, usuario.getDsSalt()));
    }

}
