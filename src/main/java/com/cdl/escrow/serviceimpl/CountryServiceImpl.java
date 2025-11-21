package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.CountryDTO;
import com.cdl.escrow.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryServiceImpl implements CountryService {
    @Override
    public Page<CountryDTO> getAllCountry(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<CountryDTO> getCountryById(Long id) {
        return Optional.empty();
    }

    @Override
    public CountryDTO saveCountry(CountryDTO countryDTO) {
        return null;
    }

    @Override
    public CountryDTO updateCountry(Long id, CountryDTO countryDTO) {
        return null;
    }

    @Override
    public Boolean deleteCountryById(Long id) {
        return null;
    }

    @Override
    public boolean softDeleteCountryById(Long id) {
        return false;
    }
}
