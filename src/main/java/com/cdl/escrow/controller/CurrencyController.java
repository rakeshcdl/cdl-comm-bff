package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.CurrencyCriteriaService;
import com.cdl.escrow.dto.CurrencyDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.CurrencyRepository;
import com.cdl.escrow.service.CurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/currency")
@RequiredArgsConstructor
@Slf4j
public class CurrencyController {

    private final CurrencyService currencyService;

    //private final CurrencyCriteriaService currencyCriteriaService;

    private final CurrencyRepository repository;

    private static final String ENTITY_NAME = "CURRENCY";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<CurrencyDTO>> getAllCurrency(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all currency , page: {}", pageable.getPageNumber());
        Page<CurrencyDTO> page = currencyService.getAllCurrency(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<CurrencyDTO> saveCurrency(
            @Valid @RequestBody CurrencyDTO dto) {
        log.info("Creating new currency ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new currency cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CurrencyDTO saved = currencyService.saveCurrency(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyDTO> getCurrencyById(@PathVariable Long id) {
        log.info("Fetching currency  with ID: {}", id);
        return currencyService.getCurrencyById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("currency not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrencyDTO> updateCurrency(
            @PathVariable Long id,
            @Valid @RequestBody CurrencyDTO dto) {
        log.info("Updating currency with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        CurrencyDTO updated = currencyService.updateCurrency(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCurrencyById(@PathVariable Long id) {
        log.info("Deleting currency with ID: {}", id);
        boolean deleted = currencyService.deleteCurrencyById(id);
        if (deleted) {
            return ResponseEntity.ok("Currency deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Currency deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteCurrencyById(@PathVariable Long id) {
        log.info("Soft deleting currency with ID: {}", id);

        boolean deleted = currencyService.softDeleteCurrencyById(id);
        if (deleted) {
            return ResponseEntity.ok("Currency soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Currency soft deletion failed - ID: " + id);
        }
    }
}
