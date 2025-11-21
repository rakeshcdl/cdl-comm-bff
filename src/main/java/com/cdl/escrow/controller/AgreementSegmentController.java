package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.AgreementSegmentCriteriaService;
import com.cdl.escrow.dto.AgreementSegmentDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.AgreementSegmentRepository;
import com.cdl.escrow.service.AgreementSegmentService;
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
@RequestMapping("/api/v1/agreement-segment")
@RequiredArgsConstructor
@Slf4j
public class AgreementSegmentController {

    private final AgreementSegmentService agreementSegmentService;

    //private final AgreementSegmentCriteriaService agreementSegmentCriteriaService;

    private final AgreementSegmentRepository repository;

    private static final String ENTITY_NAME = "AGREEMENT-SEGMENT";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<AgreementSegmentDTO>> getAllAgreementSegment(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all agreement segment , page: {}", pageable.getPageNumber());
        Page<AgreementSegmentDTO> page = agreementSegmentService.getAllAgreementSegment(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<AgreementSegmentDTO> saveAgreementSegment(
            @Valid @RequestBody AgreementSegmentDTO dto) {
        log.info("Creating new agreement segment");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new agreement segment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgreementSegmentDTO saved = agreementSegmentService.saveAgreementSegment(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgreementSegmentDTO> getAgreementSegmentById(@PathVariable Long id) {
        log.info("Fetching agreement segment with ID: {}", id);
        return agreementSegmentService.getAgreementSegmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Agreement segment not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgreementSegmentDTO> updateAgreementSegment(
            @PathVariable Long id,
            @Valid @RequestBody AgreementSegmentDTO dto) {
        log.info("Updating agreement segment with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AgreementSegmentDTO updated = agreementSegmentService.updateAgreementSegment(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgreementSegmentById(@PathVariable Long id) {
        log.info("Deleting agreement segment with ID: {}", id);
        boolean deleted = agreementSegmentService.deleteAgreementSegmentById(id);
        if (deleted) {
            return ResponseEntity.ok("AgreementSegment deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AgreementSegment deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteAgreementSegmentById(@PathVariable Long id) {
        log.info("Soft deleting agreement segment with ID: {}", id);

        boolean deleted = agreementSegmentService.softDeleteAgreementSegmentById(id);
        if (deleted) {
            return ResponseEntity.ok("AgreementSegment soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AgreementSegment soft deletion failed - ID: " + id);
        }
    }
}
