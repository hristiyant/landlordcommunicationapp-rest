package com.spiderman.landlordcommunicationapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionid")
    private int id;

    @NotNull
    @Column(name = "dateoftransaction")
    private Timestamp dateOfTransaction;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "TenantBankAccount")
    private BankAccount tenantBankAccount;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "LandlordBankAccount")
    private BankAccount landlordBankAccount;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "accommodation")
    private Accommodation accommodation;

    @NotNull
    @Column(name = "transactionamount")
    private double transactionAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Timestamp dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public BankAccount getTenantBankAccount() {
        return tenantBankAccount;
    }

    public void setTenantBankAccount(BankAccount tenantBankAccount) {
        this.tenantBankAccount = tenantBankAccount;
    }

    public BankAccount getLandlordBankAccount() {
        return landlordBankAccount;
    }

    public void setLandlordBankAccount(BankAccount landlordBankAccount) {
        this.landlordBankAccount = landlordBankAccount;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
