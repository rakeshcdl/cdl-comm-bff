package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.StandingInstructionBeneficiaryCriteria;
import com.cdl.escrow.criteriaservice.StandingInstructionBeneficiaryCriteriaService;
import com.cdl.escrow.dto.StandingInstructionBeneficiaryDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.StandingInstructionBeneficiaryRepository;
import com.cdl.escrow.service.StandingInstructionBeneficiaryService;
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
@RequestMapping("/api/v1/standing-instruction-beneficiary")
@RequiredArgsConstructor
@Slf4j
public class StandingInstructionBeneficiaryController {

    private final StandingInstructionBeneficiaryService standingInstructionBeneficiaryService;

    private final StandingInstructionBeneficiaryCriteriaService standingInstructionBeneficiaryCriteriaService;

    private final StandingInstructionBeneficiaryRepository repository;

    private static final String ENTITY_NAME = "STANDING-INSTRUCTION-BENEFICIARY";

    @GetMapping
    public ResponseEntity<Page<StandingInstructionBeneficiaryDTO>> getAllStandingInstructionBeneficiaryCriteria(@ParameterObject StandingInstructionBeneficiaryCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<StandingInstructionBeneficiaryDTO> page = standingInstructionBeneficiaryCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<StandingInstructionBeneficiaryDTO>> getAllStandingInstructionBeneficiary(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all standing instruction beneficiary , page: {}", pageable.getPageNumber());
        Page<StandingInstructionBeneficiaryDTO> page = standingInstructionBeneficiaryService.getAllStandingInstructionBeneficiary(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<StandingInstructionBeneficiaryDTO> saveStandingInstructionBeneficiary(
            @Valid @RequestBody StandingInstructionBeneficiaryDTO dto) {
        log.info("Creating new standing instruction beneficiary");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new standing instruction beneficiary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StandingInstructionBeneficiaryDTO saved = standingInstructionBeneficiaryService.saveStandingInstructionBeneficiary(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandingInstructionBeneficiaryDTO> getStandingInstructionBeneficiaryById(@PathVariable Long id) {
        log.info("Fetching standing instruction beneficiary with ID: {}", id);
        return standingInstructionBeneficiaryService.getStandingInstructionBeneficiaryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Standing instruction beneficiary not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandingInstructionBeneficiaryDTO> updateStandingInstructionBeneficiary(
            @PathVariable Long id,
            @Valid @RequestBody StandingInstructionBeneficiaryDTO dto) {
        log.info("Updating standing instruction beneficiary with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        StandingInstructionBeneficiaryDTO updated = standingInstructionBeneficiaryService.updateStandingInstructionBeneficiary(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStandingInstructionBeneficiaryById(@PathVariable Long id) {
        log.info("Deleting standing instruction beneficiary with ID: {}", id);
        boolean deleted = standingInstructionBeneficiaryService.deleteStandingInstructionBeneficiaryById(id);
        if (deleted) {
            return ResponseEntity.ok("StandingInstructionBeneficiary deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("StandingInstructionBeneficiary deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteStandingInstructionBeneficiaryById(@PathVariable Long id) {
        log.info("Soft deleting standing instruction beneficiary with ID: {}", id);

        boolean deleted = standingInstructionBeneficiaryService.softDeleteStandingInstructionBeneficiaryById(id);
        if (deleted) {
            return ResponseEntity.ok("StandingInstructionBeneficiary soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("StandingInstructionBeneficiary soft deletion failed - ID: " + id);
        }
    }
}
