package com.example.BACKClubAtletismoVillaviciosa.Controller;


import com.example.BACKClubAtletismoVillaviciosa.crud.Controller.CListOfHonorsController;
import com.example.BACKClubAtletismoVillaviciosa.crud.Interface.IListOfHonors;
import com.example.BACKClubAtletismoVillaviciosa.crud.Model.CListOfHonors;
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

@WebMvcTest(CListOfHonorsController.class)
class CListOfHonorsControllerTest {

    @InjectMocks
    private CListOfHonorsController cListOfHonorsController;

    @MockBean
    private IListOfHonors iListOfHonors;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getListOfHonors()  throws Exception{
        List<CListOfHonors> listOfHonors = new ArrayList<CListOfHonors>();
        listOfHonors.add(new CListOfHonors(1,"Description"));
        listOfHonors.add(new CListOfHonors(2,"Description"));

        Mockito.when(iListOfHonors.findAll()).thenReturn(listOfHonors);
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/listOfHonors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();


        assertTrue(response.getContentAsString().contains("Description"));
    }



    @Test
    void getListOfHonorsBy() {

        int id = 1;
        CListOfHonors listOfHonors = new CListOfHonors(1,"Description");
        Mockito.when(iListOfHonors.findById(id)).thenReturn(Optional.of(listOfHonors));

        CListOfHonors result = cListOfHonorsController.getCListOfHonorsBy(id);

        assertEquals(listOfHonors, result);
    }

    @Test
    void saveListOfHonors() throws URISyntaxException{

        CListOfHonors listOfHonors = new CListOfHonors(1,"Description");

        Mockito.when(iListOfHonors.save(listOfHonors)).thenReturn(listOfHonors);

        ResponseEntity<CListOfHonors> result = cListOfHonorsController.save(listOfHonors);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(listOfHonors, result.getBody());

    }

    @Test
    void updateListOfHonors() {
        int id = 1;
        CListOfHonors currentListOfHonors = new CListOfHonors(1,"Description");
        CListOfHonors updatedListOfHonors = new CListOfHonors(2,"Description");

        Mockito.when(iListOfHonors.findById(id)).thenReturn(Optional.of(currentListOfHonors));
        Mockito.when(iListOfHonors.save(currentListOfHonors)).thenReturn(currentListOfHonors);

        ResponseEntity<CListOfHonors> result = cListOfHonorsController.update(id, updatedListOfHonors);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedListOfHonors.getDescription(), result.getBody().getDescription());

    }

    @Test
    void deleteListOfHonors() {
        int id = 1;

        ResponseEntity result = cListOfHonorsController.delete(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        Mockito.verify(iListOfHonors).deleteById(id);


    }
}