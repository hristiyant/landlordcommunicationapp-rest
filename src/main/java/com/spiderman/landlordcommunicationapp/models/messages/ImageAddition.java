package com.spiderman.landlordcommunicationapp.models.messages;

import com.spiderman.landlordcommunicationapp.models.messages.base.Addable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "imagemessages")
public class ImageAddition implements Addable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imagemessageid")
    private int id;

    @NotNull
    @Column(name = "image")
    private byte[] imageContent;

    public ImageAddition() {
    }

    @Override
    public TypeOfAddition returnTypeOfTheAddition() {
        return TypeOfAddition.IMAGE;
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

}
