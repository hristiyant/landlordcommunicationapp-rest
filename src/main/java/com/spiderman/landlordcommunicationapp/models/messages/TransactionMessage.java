package com.spiderman.landlordcommunicationapp.models.messages;

import com.spiderman.landlordcommunicationapp.models.Transaction;
import com.spiderman.landlordcommunicationapp.models.messages.base.MessageDetail;
import com.spiderman.landlordcommunicationapp.models.messages.base.Messageable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "transactionmessages")
public class TransactionMessage implements Messageable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionmessageid")
    private int id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "transactionid")
    private Transaction transaction;

    @NotNull
    @OneToOne
    @JoinColumn(name = "messagedetailid")
    private MessageDetail messageDetail;

    @Override
    public MessageDetail returnMessageDetail() {
        return this.getMessageDetail();
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

    public MessageDetail getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(MessageDetail messageDetail) {
        this.messageDetail = messageDetail;
    }
}
