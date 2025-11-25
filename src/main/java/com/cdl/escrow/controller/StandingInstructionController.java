package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.StandingInstructionCriteria;
import com.cdl.escrow.criteriaservice.StandingInstructionCriteriaService;
import com.cdl.escrow.dto.StandingInstructionDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.StandingInstructionRepository;
import com.cdl.escrow.service.StandingInstructionService;
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
@RequestMapping("/api/v1/standing-instruction")
@RequiredArgsConstructor
@Slf4j
public class StandingInstructionController {

    private final StandingInstructionService standingInstructionService;

    private final StandingInstructionCriteriaService standingInstructionCriteriaService;

    private final StandingInstructionRepository repository;

    private static final String ENTITY_NAME = "STANDING-INSTRUCTION";

    @GetMapping
    public ResponseEntity<Page<StandingInstructionDTO>> getAllStandingInstructionCriteria(@ParameterObject StandingInstructionCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<StandingInstructionDTO> page = standingInstructionCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<StandingInstructionDTO>> getAllStandingInstruction(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all standing instruction , page: {}", pageable.getPageNumber());
        Page<StandingInstructionDTO> page = standingInstructionService.getAllStandingInstruction(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<StandingInstructionDTO> saveStandingInstruction(
            @Valid @RequestBody StandingInstructionDTO dto) {
        log.info("Creating new standing instruction ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new standing instruction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StandingInstructionDTO saved = standingInstructionService.saveStandingInstruction(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandingInstructionDTO> getStandingInstructionById(@PathVariable Long id) {
        log.info("Fetching standing instruction  with ID: {}", id);
        return standingInstructionService.getStandingInstructionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("standing instruction not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandingInstructionDTO> updateStandingInstruction(
            @PathVariable Long id,
            @Valid @RequestBody StandingInstructionDTO dto) {
        log.info("Updating standing instruction with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        StandingInstructionDTO updated = standingInstructionService.updateStandingInstruction(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStandingInstructionById(@PathVariable Long id) {
        log.info("Deleting standing instruction with ID: {}", id);
        boolean deleted = standingInstructionService.deleteStandingInstructionById(id);
        if (deleted) {
            return ResponseEntity.ok("StandingInstruction deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("StandingInstruction deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteStandingInstructionById(@PathVariable Long id) {
        log.info("Soft deleting standing instruction with ID: {}", id);

        boolean deleted = standingInstructionService.softDeleteStandingInstructionById(id);
        if (deleted) {
            return ResponseEntity.ok("StandingInstruction soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("StandingInstruction soft deletion failed - ID: " + id);
        }
    }
}
