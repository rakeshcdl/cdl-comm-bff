package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.InvestmentCriteria;
import com.cdl.escrow.criteriaservice.InvestmentCriteriaService;
import com.cdl.escrow.dto.InvestmentDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.InvestmentRepository;
import com.cdl.escrow.service.InvestmentService;
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
@RequestMapping("/api/v1/investment")
@RequiredArgsConstructor
@Slf4j
public class InvestmentController {

    private final InvestmentService investmentService;

    private final InvestmentCriteriaService investmentCriteriaService;

    private final InvestmentRepository repository;

    private static final String ENTITY_NAME = "INVESTMENT";

    @GetMapping
    public ResponseEntity<Page<InvestmentDTO>> getAllInvestmentCriteria(@ParameterObject InvestmentCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<InvestmentDTO> page = investmentCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<InvestmentDTO>> getAllInvestment(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all investment , page: {}", pageable.getPageNumber());
        Page<InvestmentDTO> page = investmentService.getAllInvestment(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<InvestmentDTO> saveInvestment(
            @Valid @RequestBody InvestmentDTO dto) {
        log.info("Creating new investment ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new investment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvestmentDTO saved = investmentService.saveInvestment(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestmentDTO> getInvestmentById(@PathVariable Long id) {
        log.info("Fetching investment with ID: {}", id);
        return investmentService.getInvestmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("investment not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestmentDTO> updateInvestment(
            @PathVariable Long id,
            @Valid @RequestBody InvestmentDTO dto) {
        log.info("Updating investment with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        InvestmentDTO updated = investmentService.updateInvestment(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvestmentById(@PathVariable Long id) {
        log.info("Deleting investment with ID: {}", id);
        boolean deleted = investmentService.deleteInvestmentById(id);
        if (deleted) {
            return ResponseEntity.ok("Investment deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Investment deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteInvestmentById(@PathVariable Long id) {
        log.info("Soft deleting investment with ID: {}", id);

        boolean deleted = investmentService.softDeleteInvestmentById(id);
        if (deleted) {
            return ResponseEntity.ok("Investment soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Investment soft deletion failed - ID: " + id);
        }
    }
}
