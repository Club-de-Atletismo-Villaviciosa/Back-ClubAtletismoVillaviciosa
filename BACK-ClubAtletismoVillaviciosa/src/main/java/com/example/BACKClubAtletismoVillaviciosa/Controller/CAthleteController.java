package com.example.BACKClubAtletismoVillaviciosa.Controller;

import com.example.BACKClubAtletismoVillaviciosa.Interface.IAthlete;
import com.example.BACKClubAtletismoVillaviciosa.Model.CAthlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/athlete")
@CrossOrigin("*")
public class CAthleteController {

    @Autowired
    private IAthlete iAthlete;

    @GetMapping
    public List<CAthlete> getAthlete (Model model){
        return iAthlete.findAll();

    }

    @GetMapping("/{id}")
    public  CAthlete getCAthleteBy(@PathVariable int id){
        return iAthlete.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/save")
    public ResponseEntity<CAthlete>save(@RequestBody CAthlete athlete)throws URISyntaxException {
        CAthlete savedAthlete = iAthlete.save(athlete);
        return ResponseEntity.created(new URI("/athelete/" + savedAthlete.getId())).body(savedAthlete);
    }

    @PutMapping("update/{id}")



}
