package com.spiderman.landlordcommunicationapp.models.messages;

import com.spiderman.landlordcommunicationapp.models.Transaction;
import com.spiderman.landlordcommunicationapp.models.messages.base.Addable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "transactionmessages")
public class TransactionAddition implements Addable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionmessageid")
    private int id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "transactionid")
    private Transaction transaction;

    @Override
    public TypeOfAddition returnTypeOfTheAddition() {
        return TypeOfAddition.TRANSACTION;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

}
