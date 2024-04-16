package com.generation.autenticacao.repository;

import org.springframework.data.repository.CrudRepository;

import com.generation.autenticacao.model.JwtToken;

public interface JwtTokenRepository extends CrudRepository<JwtToken, Long> {
    JwtToken findByToken(String token);
}
