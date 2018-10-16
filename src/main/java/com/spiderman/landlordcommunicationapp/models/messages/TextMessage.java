package com.spiderman.landlordcommunicationapp.models.messages;

import com.spiderman.landlordcommunicationapp.models.messages.base.MessageDetail;
import com.spiderman.landlordcommunicationapp.models.messages.base.Messageable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "textmessages")
public class TextMessage implements Messageable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "textmessageid")
    private int id;

    @NotNull //todo do we need it to be not null?
    @Column(name = "text")
    private String textOfTheMessage;

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

    public String getTextOfTheMessage() {
        return textOfTheMessage;
    }

    public void setTextOfTheMessage(String textOfTheMessage) {
        this.textOfTheMessage = textOfTheMessage;
    }

    public MessageDetail getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(MessageDetail messageDetail) {
        this.messageDetail = messageDetail;
    }
}
