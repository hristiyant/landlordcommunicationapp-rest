package com.spiderman.landlordcommunicationapp.models.messages.base;

public interface Addable {
    TypeOfAddition returnTypeOfTheAddition();

     enum TypeOfAddition {
        IMAGE, TRANSACTION,
    }

}
