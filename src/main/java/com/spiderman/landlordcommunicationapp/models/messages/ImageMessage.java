package com.spiderman.landlordcommunicationapp.models.messages;

import com.spiderman.landlordcommunicationapp.models.messages.base.MessageDetail;
import com.spiderman.landlordcommunicationapp.models.messages.base.Messageable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "imagemessages")
public class ImageMessage implements Messageable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imagemessageid")
    private int id;

    @NotNull
    @Column(name = "image")
    private byte[] imageContent;

    @NotNull
    @OneToOne
    @JoinColumn(name = "messagedetailid")
    private MessageDetail messageDetail;

    public ImageMessage() {
    }

    public ImageMessage(@NotNull byte[] imageContent, @NotNull MessageDetail messageDetail) {
        this.imageContent = imageContent;
        this.messageDetail = messageDetail;
    }

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

    public byte[] getImageContent() {
        return imageContent;
    }

    public void setImageContent(byte[] imageContent) {
        this.imageContent = imageContent;
    }

    public MessageDetail getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(MessageDetail messageDetail) {
        this.messageDetail = messageDetail;
    }
}
