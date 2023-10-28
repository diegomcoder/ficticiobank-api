package com.diegomd.ficticiobankapi.entities.account;

import java.util.List;
import java.util.UUID;

public class AccountModel {
    private UUID id;
    private UUID accountHolderId;
    private AccountTypes type;
    private int number;
    private boolean positiveBalance;
    private double balance;
    private List<BankingOperation> statement;
}
