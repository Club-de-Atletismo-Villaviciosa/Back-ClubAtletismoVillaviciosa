package com.example.BACKClubAtletismoVillaviciosa.Controller;


import com.example.BACKClubAtletismoVillaviciosa.Interface.INews;
import com.example.BACKClubAtletismoVillaviciosa.Model.CNews;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
@RestController
@RequestMapping("api/v1/news")
@CrossOrigin("*")
public class CNewsController {

    @Autowired
    private INews iNews;

    @GetMapping
    public List<CNews> getNews (Model model){
        return iNews.findAll();

    }

    @GetMapping("/{id}")
    public  CNews getCNewsBy(@PathVariable int id){
        return iNews.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/save")
    public ResponseEntity<CNews> save(@RequestBody CNews news)throws URISyntaxException {
        CNews savedNews = iNews.save(news);
        return ResponseEntity.created(new URI("/news/" + savedNews.getId())).body(savedNews);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CNews> update(@PathVariable int id, @RequestBody CNews news){
        CNews currentNews = iNews.findById(id).orElseThrow(RuntimeException::new);
        currentNews.setNews(news.getNews());
        currentNews.setTitle(news.getTitle());
        currentNews.setUrl(news.getUrl());

        return ResponseEntity.ok(currentNews);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete (@PathVariable int id){
        iNews.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
