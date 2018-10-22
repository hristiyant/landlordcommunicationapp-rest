package com.spiderman.landlordcommunicationapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bankaccounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bankaccountid")
    private int id;

    @NotNull
    @Column(name = "accountnumber")
    private String accountNumber;

    @NotNull
    @Column(name = "amount")
    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
