package com.example.BACKClubAtletismoVillaviciosa.crud.Interface;

import com.example.BACKClubAtletismoVillaviciosa.crud.Model.CAthlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAthlete extends JpaRepository<CAthlete, Integer> {
}
