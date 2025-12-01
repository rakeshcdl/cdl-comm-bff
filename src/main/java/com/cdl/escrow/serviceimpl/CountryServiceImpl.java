package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.CountryDTO;
import com.cdl.escrow.entity.Country;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.CountryMapper;
import com.cdl.escrow.repository.CountryRepository;
import com.cdl.escrow.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repository;

    private final CountryMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Page<CountryDTO> getAllCountry(Pageable pageable) {

        log.debug("Fetching all country, page: {}", pageable.getPageNumber());
        Page<Country> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CountryDTO> getCountryById(Long id) {

        log.debug("Fetching country with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public CountryDTO saveCountry(CountryDTO countryDTO) {
        log.info("Saving new country");
        Country entity = mapper.toEntity(countryDTO);
        Country saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public CountryDTO updateCountry(Long id, CountryDTO countryDTO) {
        log.info("Updating country with ID: {}", id);

        Country existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("country not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        Country toUpdate = mapper.toEntity(countryDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        Country updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteCountryById(Long id) {
        log.info("Deleting country with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("country not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeleteCountryById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
