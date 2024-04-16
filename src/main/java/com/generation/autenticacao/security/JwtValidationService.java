package com.generation.autenticacao.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.autenticacao.model.JwtToken;
import com.generation.autenticacao.repository.JwtTokenRepository;

@Service
public class JwtValidationService {

    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    public boolean validarJwt(String jwt) {
        try {
            JwtToken token = jwtTokenRepository.findByToken(jwt);

            if (token == null) {
                return false; // JWT não encontrado no banco de dados
            }

            // Verificar se o JWT atende às regras especificadas
            return validarClaims(token);
        } catch (Exception e) {
            return false; // Em caso de erro na validação
        }
    }

    private boolean validarClaims(JwtToken token) {
        // Verificar as regras para cada claim
        String name = token.getName();
        String role = token.getRole();
        String seed = token.getSeed();

        return validarNome(name) && validarPapel(role) && validarSeed(seed);
    }

    private boolean validarNome(String nome) {
        // Verificar se o nome não contém caracteres numéricos e tem no máximo 256 caracteres
        return nome.matches("[^\\d]+") && nome.length() <= 256;
    }

    private boolean validarPapel(String papel) {
        // Verificar se o papel é válido (Admin, Member ou External)
        return papel.equals("Admin") || papel.equals("Member") || papel.equals("External");
    }

    private boolean validarSeed(String seed) {
        // Verificar se o valor da Seed é um número primo
        try {
            int num = Integer.parseInt(seed);
            return isPrimo(num);
        } catch (NumberFormatException e) {
            return false; // Em caso de erro ao converter para número
        }
    }

    private boolean isPrimo(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}