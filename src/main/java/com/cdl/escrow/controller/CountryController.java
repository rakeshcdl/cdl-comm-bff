package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.CountryCriteria;
import com.cdl.escrow.criteriaservice.CountryCriteriaService;
import com.cdl.escrow.dto.CountryDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.CountryRepository;
import com.cdl.escrow.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/country")
@RequiredArgsConstructor
@Slf4j
public class CountryController {

    private final CountryService countryService;

   private final CountryCriteriaService countryCriteriaService;

    private final CountryRepository repository;

    private static final String ENTITY_NAME = "COUNTRY";

    @GetMapping
    public ResponseEntity<Page<CountryDTO>> getAllCountryCriteria(@ParameterObject CountryCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<CountryDTO> page = countryCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<CountryDTO>> getAllCountry(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all country , page: {}", pageable.getPageNumber());
        Page<CountryDTO> page = countryService.getAllCountry(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<CountryDTO> saveCountry(
            @Valid @RequestBody CountryDTO dto) {
        log.info("Creating new country ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new country cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CountryDTO saved = countryService.saveCountry(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable Long id) {
        log.info("Fetching country with ID: {}", id);
        return countryService.getCountryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("country not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> updateCountry(
            @PathVariable Long id,
            @Valid @RequestBody CountryDTO dto) {
        log.info("Updating country with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        CountryDTO updated = countryService.updateCountry(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountryById(@PathVariable Long id) {
        log.info("Deleting country with ID: {}", id);
        boolean deleted = countryService.deleteCountryById(id);
        if (deleted) {
            return ResponseEntity.ok("Country deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Country deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteCountryById(@PathVariable Long id) {
        log.info("Soft deleting country with ID: {}", id);

        boolean deleted = countryService.softDeleteCountryById(id);
        if (deleted) {
            return ResponseEntity.ok("Country soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Country soft deletion failed - ID: " + id);
        }
    }
}
