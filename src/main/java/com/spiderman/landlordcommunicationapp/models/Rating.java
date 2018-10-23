package com.spiderman.landlordcommunicationapp.models;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingid")
    private int id;

    @Column(name = "rating")
    private double rating;

    @ManyToOne
    @JoinColumn(name = "rateduser")
    private User ratedUser;

    @ManyToOne
    @JoinColumn(name = "sourceuser")
    private User sourceUser;

    public Rating() {
    }

    public Rating(User ratedUser, User sourceUser, double rating) {
        this.rating = rating;
        this.ratedUser = ratedUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public User getRatedUser() {
        return ratedUser;
    }

    public void setRatedUser(User ratedUser) {
        this.ratedUser = ratedUser;
    }

    public User getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(User sourceUser) {
        this.sourceUser = sourceUser;
    }
}
