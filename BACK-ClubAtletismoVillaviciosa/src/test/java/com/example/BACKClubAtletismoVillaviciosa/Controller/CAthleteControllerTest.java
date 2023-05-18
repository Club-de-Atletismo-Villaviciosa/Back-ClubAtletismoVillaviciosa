package com.example.BACKClubAtletismoVillaviciosa.Controller;


import com.example.BACKClubAtletismoVillaviciosa.crud.Controller.CAthleteController;
import com.example.BACKClubAtletismoVillaviciosa.crud.Interface.IAthlete;
import com.example.BACKClubAtletismoVillaviciosa.crud.Model.CAthlete;
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

@WebMvcTest(CAthleteController.class)
class CAthleteControllerTest {

    @InjectMocks
    private CAthleteController cAthleteController;

    @MockBean
    private IAthlete iAthlete;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetAthlete() throws Exception {
        List<CAthlete> athletes = new ArrayList<CAthlete>();
        athletes.add(new CAthlete(1,"url", "Category", "Discipline", "name", "LicenseNumber"));
        athletes.add(new CAthlete(2,"url", "Category", "Discipline", "name", "LicenseNumber"));

        Mockito.when(iAthlete.findAll()).thenReturn(athletes);
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/athlete")
                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                .andReturn()
                .getResponse();


        assertTrue(response.getContentAsString().contains("url"));
    }
    @Test
    public void testGetCAthleteBy() {
        int id = 1;
        CAthlete athlete = new CAthlete(1,"url", "Category", "Discipline", "name", "LicenseNumber");
        Mockito.when(iAthlete.findById(id)).thenReturn(Optional.of(athlete));

        CAthlete result = cAthleteController.getCAthleteBy(id);

        assertEquals(athlete, result);
    }

    @Test
    public void testSaveAthlete() throws URISyntaxException {
        CAthlete athlete = new CAthlete(1,"url", "Category", "Discipline", "name", "LicenseNumber");

        Mockito.when(iAthlete.save(athlete)).thenReturn(athlete);

        ResponseEntity<CAthlete> result = cAthleteController.save(athlete);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(athlete, result.getBody());
    }

    @Test
    public void testUpdateAthlete() {
        int id = 1;
        CAthlete currentAthlete = new CAthlete(1,"url", "Category", "Discipline", "name", "LicenseNumber");
        CAthlete updatedAthlete = new CAthlete(2,"url", "Category", "Discipline", "name", "LicenseNumber");

        Mockito.when(iAthlete.findById(id)).thenReturn(Optional.of(currentAthlete));
        Mockito.when(iAthlete.save(currentAthlete)).thenReturn(currentAthlete);

        ResponseEntity<CAthlete> result = cAthleteController.update(id, updatedAthlete);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedAthlete.getName(), result.getBody().getName());
        assertEquals(updatedAthlete.getCategory(), result.getBody().getCategory());
        assertEquals(updatedAthlete.getDiscipline(), result.getBody().getDiscipline());
        assertEquals(updatedAthlete.getUrl(), result.getBody().getUrl());
        assertEquals(updatedAthlete.getLicenseNumber(), result.getBody().getLicenseNumber());
    }

    @Test
    public void testDeleteAthlete() {
        int id = 1;

        ResponseEntity result = cAthleteController.delete(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        Mockito.verify(iAthlete).deleteById(id);
    }
}