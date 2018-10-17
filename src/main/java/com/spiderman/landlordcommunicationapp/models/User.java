package com.spiderman.landlordcommunicationapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
//@SecondaryTable(name = "ratings")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int id;

    @NotNull
    @Size(min = 2, message = "User's first name should be at least 2 characters long!")
    @Column(name = "firstname")
    private String firstName;

    @Size(min = 2, message = "User's middle name should be at least 2 characters long!")
    @Column(name = "middlename")
    private String middleName;

    @NotNull
    @Size(min = 2, message = "User's last name should be at least 2 characters long!")
    @Column(name = "lastname")
    private String lastName;

    @NotNull
    @Size(min = 10, max = 10, message = "Phone number should be in format 0888123456 - 10 digits long")
    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "islandlord")
    private Boolean isLandlord;

    @OneToOne
    @JoinColumn(name = "bankaccountid")
    private BankAccount bankAccount;


    //todo double check this section
//    how to/or is it even possible to fill the List<Double> from table rating without creating Rating entity?
//    @OneToMany
//    @JoinColumn(name = "userid", table = "ratings")
//    @Column(table = "ratings", name = "rating")
    @OneToMany
    @JoinColumn(name = "userid")
    private List<Rating> ratings;

    @OneToMany
    @JoinColumn(name = "userid")
    private List<Accommodation> accommodations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getLandlord() {
        return isLandlord;
    }

    public void setLandlord(Boolean landlord) {
        isLandlord = landlord;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Accommodation> getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(List<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }
}
