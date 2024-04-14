package com.generation.autenticacao.model;


class Jwt {
String name;
String role;
long seed;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public long getSeed() {
	return seed;
}
public void setSeed(long seed) {
	this.seed = seed;
}
}