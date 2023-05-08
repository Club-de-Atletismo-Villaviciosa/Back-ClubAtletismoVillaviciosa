package com.example.BACKClubAtletismoVillaviciosa.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "Athlete")
public class CAthlete {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;
    @Column(columnDefinition = "LONGTEXT")
    private String url;
    private String category;
    private String discipline;
    private String licenseNumber;

  public CAthlete (int id, String url, String category, String discipline, String name, String licenseNumber){

      this.discipline=discipline;
      this.id=id;
      this.category=category;

      this.url=url;
      this.name=name;
      this.licenseNumber=licenseNumber;
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

    public String getDiscipline() {
        return discipline;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

}
