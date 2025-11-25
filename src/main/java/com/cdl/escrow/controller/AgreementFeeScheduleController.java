package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.AgreementFeeScheduleCriteria;
import com.cdl.escrow.criteriaservice.AgreementFeeScheduleCriteriaService;
import com.cdl.escrow.dto.AgreementFeeScheduleDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.AgreementFeeScheduleRepository;
import com.cdl.escrow.service.AgreementFeeScheduleService;
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
@RequestMapping("/api/v1/agreement-fee-schedule")
@RequiredArgsConstructor
@Slf4j
public class AgreementFeeScheduleController {

    private final AgreementFeeScheduleService agreementFeeScheduleService;

    private final AgreementFeeScheduleCriteriaService agreementFeeScheduleCriteriaService;

    private final AgreementFeeScheduleRepository repository;

    private static final String ENTITY_NAME = "AGREEMENT-FEE-SCHEDULE";

   @GetMapping
    public ResponseEntity<Page<AgreementFeeScheduleDTO>> getAllAgreementFeeScheduleCriteria(@ParameterObject AgreementFeeScheduleCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AgreementFeeScheduleDTO> page = agreementFeeScheduleCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<AgreementFeeScheduleDTO>> getAllAgreementFeeSchedule(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all agreement fee schedule , page: {}", pageable.getPageNumber());
        Page<AgreementFeeScheduleDTO> page = agreementFeeScheduleService.getAllAgreementFeeSchedule(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<AgreementFeeScheduleDTO> saveAgreementFeeSchedule(
            @Valid @RequestBody AgreementFeeScheduleDTO dto) {
        log.info("Creating new agreement fee schedule ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new agreement fee schedule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgreementFeeScheduleDTO saved = agreementFeeScheduleService.saveAgreementFeeSchedule(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgreementFeeScheduleDTO> getAgreementFeeScheduleById(@PathVariable Long id) {
        log.info("Fetching agreement fee schedule  with ID: {}", id);
        return agreementFeeScheduleService.getAgreementFeeScheduleById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Agreement fee schedule not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgreementFeeScheduleDTO> updateAgreementFeeSchedule(
            @PathVariable Long id,
            @Valid @RequestBody AgreementFeeScheduleDTO dto) {
        log.info("Updating agreement fee schedule with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AgreementFeeScheduleDTO updated = agreementFeeScheduleService.updateAgreementFeeSchedule(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgreementFeeScheduleById(@PathVariable Long id) {
        log.info("Deleting agreement fee schedule with ID: {}", id);
        boolean deleted = agreementFeeScheduleService.deleteAgreementFeeScheduleById(id);
        if (deleted) {
            return ResponseEntity.ok("AccountType deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AccountType deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteAgreementFeeScheduleById(@PathVariable Long id) {
        log.info("Soft deleting agreement fee schedule with ID: {}", id);

        boolean deleted = agreementFeeScheduleService.softDeleteAgreementFeeScheduleById(id);
        if (deleted) {
            return ResponseEntity.ok("AccountType soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AccountType soft deletion failed - ID: " + id);
        }
    }
}
