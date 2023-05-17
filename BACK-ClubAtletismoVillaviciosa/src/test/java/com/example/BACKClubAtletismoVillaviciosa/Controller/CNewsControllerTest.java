package com.example.BACKClubAtletismoVillaviciosa.Controller;

import com.example.BACKClubAtletismoVillaviciosa.Interface.IAthlete;
import com.example.BACKClubAtletismoVillaviciosa.Interface.INews;
import com.example.BACKClubAtletismoVillaviciosa.Model.CAthlete;
import com.example.BACKClubAtletismoVillaviciosa.Model.CNews;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(CNewsController.class)
class CNewsControllerTest {


    @InjectMocks
    private CNewsController cNewsController;

    @MockBean
    private INews iNews;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getNews() throws Exception{

        List<CNews> news = new ArrayList<CNews>();
        news.add(new CNews(1,"news", "url", "title"));
        news.add(new CNews(2,"news", "url", "title"));

        Mockito.when(iNews.findAll()).thenReturn(news);
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/news")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();


        assertTrue(response.getContentAsString().contains("title"));


    }

    @Test
    void getCNewsBy() {

        int id = 1;
        CNews news = new CNews(1,"news", "url", "title");
        Mockito.when(iNews.findById(id)).thenReturn(Optional.of(news));

        CNews result = cNewsController.getCNewsBy(id);

        assertEquals(news, result);
    }

    @Test
    void saveNews() throws URISyntaxException{

        CNews news = new CNews(1,"news", "url", "title");

        Mockito.when(iNews.save(news)).thenReturn(news);

        ResponseEntity<CNews> result = cNewsController.save(news);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(news, result.getBody());

    }

    @Test
    void updateNews() {

        int id = 1;
        CNews currentNews = new CNews(1,"news", "url", "title");
        CNews updatedNews = new CNews(2,"news", "url", "title");

        Mockito.when(iNews.findById(id)).thenReturn(Optional.of(currentNews));
        Mockito.when(iNews.save(currentNews)).thenReturn(currentNews);

        ResponseEntity<CNews> result = cNewsController.update(id, updatedNews);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedNews.getNews(), result.getBody().getNews());
        assertEquals(updatedNews.getTitle(), result.getBody().getTitle());
        assertEquals(updatedNews.getUrl(), result.getBody().getUrl());


    }

    @Test
    void deleteNews() {

        int id = 1;

        ResponseEntity result = cNewsController.delete(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        Mockito.verify(iNews).deleteById(id);
    }
}