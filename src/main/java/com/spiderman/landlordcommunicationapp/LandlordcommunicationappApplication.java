package com.spiderman.landlordcommunicationapp;

import com.spiderman.landlordcommunicationapp.models.*;
import com.spiderman.landlordcommunicationapp.models.messages.ImageMessage;
import com.spiderman.landlordcommunicationapp.models.messages.TextMessage;
import com.spiderman.landlordcommunicationapp.models.messages.TransactionMessage;
import com.spiderman.landlordcommunicationapp.models.messages.base.MessageDetail;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LandlordcommunicationappApplication {

    public static void main(String[] args) {
        SpringApplication.run(LandlordcommunicationappApplication.class, args);
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Accomodation.class)
                .addAnnotatedClass(Transaction.class)
                .addAnnotatedClass(Location.class)
                .addAnnotatedClass(BankAccount.class)
                .addAnnotatedClass(ImageMessage.class)
                .addAnnotatedClass(TextMessage.class)
                .addAnnotatedClass(TransactionMessage.class)
                .addAnnotatedClass(MessageDetail.class)
                .buildSessionFactory();
    }
}
