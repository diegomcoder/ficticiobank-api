package com.diegomd.ficticiobankapi.entities;

public class Cliente {
    private String nome;
    private double cpf;

    public Cliente(String nome, double cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    public String getNome() { return this.nome; }
    public double getCpf() { return this.cpf; }
}