package com.example.BACKClubAtletismoVillaviciosa.Model;


import jakarta.persistence.*;

@Entity
@Table(name="listOfHonors")
public class CListOfHonors {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 10000)
    private String description;


    public CListOfHonors(int id, String description){
        this.id= id;
        this.description=description;
    }

    public CListOfHonors(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
