package com.diegomd.ficticiobankapi.entities.client;

import com.diegomd.ficticiobankapi.entities.account.AccountModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class ClientModel {
    private UUID id;
    private long cpf;
    private String fullName;
    private String password;
    private List<AccountModel> accounts;
    private LocalDateTime createdAt;

    public ClientModel(String fullName, long cpf, String password){
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.fullName = fullName;
        this.cpf = cpf;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AccountModel> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountModel> accounts) {
        this.accounts = accounts;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "ClientModel{" +
                "id=" + id +
                ", cpf=" + cpf +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", accounts=" + accounts +
                ", createdAt=" + createdAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) +
                '}';
    }
}
