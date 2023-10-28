package com.diegomd.ficticiobankapi.entities;

public class Conta {
    private double saldo;
    private int numeroConta;
    private Cliente cliente;

    // CONSTRUTOR
    public Conta(Cliente titular) {
        this.cliente = titular;
        this.numeroConta = gerarNumeroConta();
        this.saldo = 0.0;
    }

    private int gerarNumeroConta() {
        String token = String.valueOf(Math.pow(this.cliente.getCpf() / 13, 2));
        return Integer.parseInt(token.substring(0,8).replace(".", ""));
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public int getNumConta() { return this.numeroConta; }

    public double getSaldo() { return this.saldo; }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public boolean sacar(double valor) {
        if (valor > this.saldo)
            return false;
        this.saldo -= valor;
        return true;
    }

    public boolean transferir(double valor, Conta conta) {
        if (valor > this.saldo)
            return false;
        conta.depositar(this.saldo - (this.saldo -= valor));
        return true;
    }
}