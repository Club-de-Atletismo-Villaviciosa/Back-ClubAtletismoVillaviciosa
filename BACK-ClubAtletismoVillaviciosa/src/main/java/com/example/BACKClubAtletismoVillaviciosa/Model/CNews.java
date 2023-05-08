package com.example.BACKClubAtletismoVillaviciosa.Model;


import jakarta.persistence.*;

@Entity
@Table(name= "News")
public class CNews {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(columnDefinition = "LONGTEXT")
    private String news;
    @Column(columnDefinition = "LONGTEXT")
    private String url;
    private String title;

    public CNews (int id, String news,String url, String title) {
        this.id=id;
        this.news=news;
        this.title=title;
        this.url=url;

    }
    public CNews (){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
