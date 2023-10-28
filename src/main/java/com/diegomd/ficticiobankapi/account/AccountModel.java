package com.diegomd.ficticiobankapi.account;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AccountModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private AccountTypes type;
    private int number;
    private boolean positiveBalance;
    private double balance;
    private List<BankingOperation> statement;
}
