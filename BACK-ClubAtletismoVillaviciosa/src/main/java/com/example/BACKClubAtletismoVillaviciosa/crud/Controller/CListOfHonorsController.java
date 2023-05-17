package com.example.BACKClubAtletismoVillaviciosa.crud.Controller;
import org.springframework.http.HttpHeaders;

import com.example.BACKClubAtletismoVillaviciosa.crud.Interface.IListOfHonors;
import com.example.BACKClubAtletismoVillaviciosa.crud.Model.CListOfHonors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("api/v1/listOfHonors")
@CrossOrigin("*")
public class CListOfHonorsController {

    @Autowired
    private IListOfHonors iListOfHonors;

    @GetMapping
    public ResponseEntity<List<CListOfHonors>> getListOfHonors (Model model){
        List<CListOfHonors> list = iListOfHonors.findAll();
        System.out.println();
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(list);
    }

    @GetMapping("/{id}")
    public CListOfHonors getCListOfHonorsBy(@PathVariable int id){
        return iListOfHonors.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/save")
    public ResponseEntity<CListOfHonors> save(@RequestBody CListOfHonors listOfHonors)throws URISyntaxException {
        CListOfHonors savedListOfHonors = iListOfHonors.save(listOfHonors);
        return ResponseEntity.created(new URI("/listOfHonors/" + savedListOfHonors.getId())).body(listOfHonors);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CListOfHonors> update(@PathVariable int id, @RequestBody CListOfHonors listOfHonors){
        CListOfHonors currentListOfHonors = iListOfHonors.findById(id).orElseThrow(RuntimeException::new);
        currentListOfHonors.setDescription(listOfHonors.getDescription());
        currentListOfHonors.setId(id);
        iListOfHonors.save(currentListOfHonors);
        return ResponseEntity.ok(currentListOfHonors);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete (@PathVariable int id){
        iListOfHonors.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
