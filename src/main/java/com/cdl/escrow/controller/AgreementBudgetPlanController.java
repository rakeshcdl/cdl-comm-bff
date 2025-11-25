package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.AgreementBudgetPlanCriteria;
import com.cdl.escrow.criteriaservice.AgreementBudgetPlanCriteriaService;
import com.cdl.escrow.dto.AgreementBudgetPlanDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.AgreementBudgetPlanRepository;
import com.cdl.escrow.service.AgreementBudgetPlanService;
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
@RequestMapping("/api/v1/agreement-budget-plan")
@RequiredArgsConstructor
@Slf4j
public class AgreementBudgetPlanController {

    private final AgreementBudgetPlanService agreementBudgetPlanService;

    private final AgreementBudgetPlanCriteriaService agreementBudgetPlanCriteriaService;

    private final AgreementBudgetPlanRepository repository;

    private static final String ENTITY_NAME = "AGREEMENT-BUDGET-PLAN";

    @GetMapping
    public ResponseEntity<Page<AgreementBudgetPlanDTO>> getAllAgreementBudgetPlanCriteria(@ParameterObject AgreementBudgetPlanCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AgreementBudgetPlanDTO> page = agreementBudgetPlanCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<AgreementBudgetPlanDTO>> getAllAgreementBudgetPlan(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all agreement budget plan , page: {}", pageable.getPageNumber());
        Page<AgreementBudgetPlanDTO> page = agreementBudgetPlanService.getAllAgreementBudgetPlan(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<AgreementBudgetPlanDTO> saveAgreementBudgetPlan(
            @Valid @RequestBody AgreementBudgetPlanDTO dto) {
        log.info("Creating new agreement budget plan ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new agreement budget plan  cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgreementBudgetPlanDTO saved = agreementBudgetPlanService.saveAgreementBudgetPlan(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgreementBudgetPlanDTO> getAgreementBudgetPlanById(@PathVariable Long id) {
        log.info("Fetching agreement budget plan  with ID: {}", id);
        return agreementBudgetPlanService.getAgreementBudgetPlanById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("agreement budget plan not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgreementBudgetPlanDTO> updateAgreementBudgetPlan(
            @PathVariable Long id,
            @Valid @RequestBody AgreementBudgetPlanDTO dto) {
        log.info("Updating agreement budget plan with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AgreementBudgetPlanDTO updated = agreementBudgetPlanService.updateAgreementBudgetPlan(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgreementBudgetPlanById(@PathVariable Long id) {
        log.info("Deleting agreement budget plan with ID: {}", id);
        boolean deleted = agreementBudgetPlanService.deleteAgreementBudgetPlanById(id);
        if (deleted) {
            return ResponseEntity.ok(" AgreementBudgetPlan deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body(" AgreementBudgetPlan deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteAgreementBudgetPlanById(@PathVariable Long id) {
        log.info("Soft deleting agreement budget plan with ID: {}", id);

        boolean deleted = agreementBudgetPlanService.softDeleteAgreementBudgetPlanById(id);
        if (deleted) {
            return ResponseEntity.ok(" AgreementBudgetPlan soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body(" AgreementBudgetPlan soft deletion failed - ID: " + id);
        }
    }
}
