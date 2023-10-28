package com.diegomd.ficticiobankapi.database;

import com.diegomd.ficticiobankapi.entities.Conta;

import java.util.ArrayList;

public class DataBase {
    private final ArrayList<Conta> correntistas;

    // CONSTRUTOR
    public DataBase() {
        this.correntistas = new ArrayList<>();
    }

    public void addCorrentista(Conta conta) {
        this.correntistas.add(conta);
    }

    public ArrayList<Conta> getCorrentistas() { return this.correntistas; }

    public Conta getConta(int numConta, double cpf) {
        for (Conta c : correntistas)
            if (c.getNumConta() == numConta || c.getCliente().getCpf() == cpf)
                return c;
        return null;
    }
}