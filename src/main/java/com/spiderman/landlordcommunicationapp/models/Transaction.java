package com.spiderman.landlordcommunicationapp.models;

import java.sql.Timestamp;

public class Transaction {

    private Timestamp dateOfTransaction;
    private BankAccount tenantBankAccount;
    private BankAccount landlordBankAccount;
    private double transactionAmount;


}
