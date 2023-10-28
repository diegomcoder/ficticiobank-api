package com.diegomd.ficticiobankapi.models.response;


import com.diegomd.ficticiobankapi.entities.account.AccountTypes;
import com.diegomd.ficticiobankapi.entities.account.BankingOperation;

import java.util.ArrayList;
import java.util.UUID;

public class Account {
    public UUID accountId;
    public long accountNumber;
    public AccountTypes accountType;
    public boolean hasPositiveBalance;
    public double balance;
    public ArrayList<BankingOperation> statement;
}
