package com.example.BACKClubAtletismoVillaviciosa.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "Athlete")
public class CAthlete {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;
    private String url;
    private String category;
    private String athleticDiscipline;

  public CAthlete (int id, String url, String category, String athleticDiscipline, String name){

      this.athleticDiscipline=athleticDiscipline;
      this.id=id;
      this.category=category;
      this.url=url;
      this.name=name;

  }

  public CAthlete(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAthleticDiscipline() {
        return athleticDiscipline;
    }

    public void setAthleticDiscipline(String athleticDiscipline) {
        this.athleticDiscipline = athleticDiscipline;
    }

}
