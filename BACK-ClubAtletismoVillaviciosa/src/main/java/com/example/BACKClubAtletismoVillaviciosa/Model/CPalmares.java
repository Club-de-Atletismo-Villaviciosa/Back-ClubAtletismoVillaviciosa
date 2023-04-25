package com.example.BACKClubAtletismoVillaviciosa.Model;


import jakarta.persistence.*;

@Entity
@Table(name="Palamares")
public class CPalmares {





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 3000)
    private String description;

    public CPalmares (int id, String description){
        this.id= id;
        this.description=description;
    }
    public CPalmares (){}

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
