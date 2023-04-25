package com.example.BACKClubAtletismoVillaviciosa.Interface;

import com.example.BACKClubAtletismoVillaviciosa.Model.CAthlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAthlete extends JpaRepository<CAthlete, Integer> {
}
