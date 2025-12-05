package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.UnitaryPaymentInstructionCriteria;
import com.cdl.escrow.criteriaservice.UnitaryPaymentInstructionCriteriaService;
import com.cdl.escrow.dto.UnitaryPaymentInstructionDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.UnitaryPaymentInstructionRepository;
import com.cdl.escrow.service.UnitaryPaymentInstructionService;
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
@RequestMapping("/api/v1/unitary-payment-instruction")
@RequiredArgsConstructor
@Slf4j
public class UnitaryPaymentInstructionController {

    private final UnitaryPaymentInstructionService unitaryPaymentInstructionService;

    private final UnitaryPaymentInstructionCriteriaService unitaryPaymentInstructionCriteriaService;

    private final UnitaryPaymentInstructionRepository repository;

    private static final String ENTITY_NAME = "UNITARY-PAYMENT-INSTRUCTION";

    @GetMapping
    public ResponseEntity<Page<UnitaryPaymentInstructionDTO>> getAllStandingInstructionCriteria(@ParameterObject UnitaryPaymentInstructionCriteria criteria,
                                                                                                @ParameterObject Pageable pageable) {
        Page<UnitaryPaymentInstructionDTO> page = unitaryPaymentInstructionCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<UnitaryPaymentInstructionDTO>> getAllStandingInstruction(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Unitary Payment Instruction , page: {}", pageable.getPageNumber());
        Page<UnitaryPaymentInstructionDTO> page = unitaryPaymentInstructionService.getAllUnitaryPaymentInstruction(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<UnitaryPaymentInstructionDTO> saveStandingInstruction(
            @Valid @RequestBody UnitaryPaymentInstructionDTO dto) {
        log.info("Creating new Unitary Payment Instruction ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Unitary Payment Instruction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UnitaryPaymentInstructionDTO saved = unitaryPaymentInstructionService.saveUnitaryPaymentInstruction(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitaryPaymentInstructionDTO> getStandingInstructionById(@PathVariable Long id) {
        log.info("Fetching Unitary Payment Instruction  with ID: {}", id);
        return unitaryPaymentInstructionService.getUnitaryPaymentInstructionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Unitary Payment Instruction not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitaryPaymentInstructionDTO> updateStandingInstruction(
            @PathVariable Long id,
            @Valid @RequestBody UnitaryPaymentInstructionDTO dto) {
        log.info("Updating Unitary Payment Instruction with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        UnitaryPaymentInstructionDTO updated = unitaryPaymentInstructionService.updateUnitaryPaymentInstruction(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStandingInstructionById(@PathVariable Long id) {
        log.info("Deleting Unitary Payment Instruction with ID: {}", id);
        boolean deleted = unitaryPaymentInstructionService.deleteUnitaryPaymentInstructionById(id);
        if (deleted) {
            return ResponseEntity.ok("Unitary Payment Instruction deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Unitary Payment Instruction deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteStandingInstructionById(@PathVariable Long id) {
        log.info("Soft deleting Unitary Payment Instruction with ID: {}", id);

        boolean deleted = unitaryPaymentInstructionService.softDeleteUnitaryPaymentInstructionById(id);
        if (deleted) {
            return ResponseEntity.ok("Unitary Payment Instruction soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Unitary Payment Instruction soft deletion failed - ID: " + id);
        }
    }
}
