package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.EscrowAgreementCriteria;
import com.cdl.escrow.criteriaservice.EscrowAgreementCriteriaService;
import com.cdl.escrow.dto.EscrowAgreementDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.EscrowAgreementRepository;
import com.cdl.escrow.service.EscrowAgreementService;
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
@RequestMapping("/api/v1/escrow-agreement")
@RequiredArgsConstructor
@Slf4j
public class EscrowAgreementController {

    private final EscrowAgreementService escrowAgreementService;

    private final EscrowAgreementCriteriaService escrowAgreementCriteriaService;

    private final EscrowAgreementRepository repository;

    private static final String ENTITY_NAME = "ESCROW-AGREEMENT";

    @GetMapping
    public ResponseEntity<Page<EscrowAgreementDTO>> getAllEscrowAgreementCriteria(@ParameterObject EscrowAgreementCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<EscrowAgreementDTO> page = escrowAgreementCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<EscrowAgreementDTO>> getAllEscrowAgreement(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all escrow agreement , page: {}", pageable.getPageNumber());
        Page<EscrowAgreementDTO> page = escrowAgreementService.getAllEscrowAgreement(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<EscrowAgreementDTO> saveEscrowAgreement(
            @Valid @RequestBody EscrowAgreementDTO dto) {
        log.info("Creating new escrow agreement ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new escrow agreement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EscrowAgreementDTO saved = escrowAgreementService.saveEscrowAgreement(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscrowAgreementDTO> getEscrowAgreementById(@PathVariable Long id) {
        log.info("Fetching escrow agreement with ID: {}", id);
        return escrowAgreementService.getEscrowAgreementById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("escrow agreement not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscrowAgreementDTO> updateEscrowAgreement(
            @PathVariable Long id,
            @Valid @RequestBody EscrowAgreementDTO dto) {
        log.info("Updating escrow agreement with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        EscrowAgreementDTO updated = escrowAgreementService.updateEscrowAgreement(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEscrowAgreementById(@PathVariable Long id) {
        log.info("Deleting escrow agreement with ID: {}", id);
        boolean deleted = escrowAgreementService.deleteEscrowAgreementById(id);
        if (deleted) {
            return ResponseEntity.ok("EscrowAgreement deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("EscrowAgreement deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteEscrowAgreementById(@PathVariable Long id) {
        log.info("Soft deleting escrow agreement with ID: {}", id);

        boolean deleted = escrowAgreementService.softDeleteEscrowAgreementById(id);
        if (deleted) {
            return ResponseEntity.ok("EscrowAgreement soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("EscrowAgreement soft deletion failed - ID: " + id);
        }
    }
}
