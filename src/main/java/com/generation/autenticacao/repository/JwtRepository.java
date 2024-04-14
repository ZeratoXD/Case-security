package com.generation.autenticacao.repository;

interface JwtRepository {
boolean validate(String jwt);
}