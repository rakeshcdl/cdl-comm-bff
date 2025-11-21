package com.cdl.escrow.service;

import com.cdl.escrow.dto.CountryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CountryService {

    Page<CountryDTO> getAllCountry(final Pageable pageable);

    Optional<CountryDTO> getCountryById(Long id);

    CountryDTO saveCountry(CountryDTO countryDTO);

    CountryDTO updateCountry(Long id, CountryDTO countryDTO);

    Boolean deleteCountryById(Long id);

    boolean softDeleteCountryById(Long id);
}
