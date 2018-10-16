package com.spiderman.landlordcommunicationapp.models.messages.base;

import com.spiderman.landlordcommunicationapp.models.Accomodation;
import com.spiderman.landlordcommunicationapp.models.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "messagedetails")
public class MessageDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messagedetailid")
    private int id;

    @NotNull
    @Column(name = "timesent")
    private Timestamp timeSent;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User tenant;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User landlord;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "accomodationid")
    private Accomodation contextAccomodation;

}
