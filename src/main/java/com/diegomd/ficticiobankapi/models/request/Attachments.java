package com.diegomd.ficticiobankapi.models.request;

import com.diegomd.ficticiobankapi.account.AccountTypes;

public class Attachments {
    public int accountNumber;
    public AccountTypes accountType;
    public String fullName;
    public long cpf;
    public String password;
    public double value;
    public String recipientIdentifier;
    public String newPassword;
    public String accessRecoverToken;
}
