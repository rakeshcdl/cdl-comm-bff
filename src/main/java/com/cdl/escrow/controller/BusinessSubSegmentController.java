package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.BusinessSubSegmentCriteria;
import com.cdl.escrow.criteriaservice.BusinessSubSegmentCriteriaService;
import com.cdl.escrow.dto.BusinessSubSegmentDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.BusinessSubSegmentRepository;
import com.cdl.escrow.service.BusinessSubSegmentService;
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
@RequestMapping("/api/v1/business-sub-segment")
@RequiredArgsConstructor
@Slf4j
public class BusinessSubSegmentController {

    private final BusinessSubSegmentService businessSubSegmentService;

    private final BusinessSubSegmentCriteriaService businessSubSegmentCriteriaService;

    private final BusinessSubSegmentRepository repository;

    private static final String ENTITY_NAME = "BUSINESS-SUB-SEGMENT";

    @GetMapping
    public ResponseEntity<Page<BusinessSubSegmentDTO>> getAllBusinessSubSegmentCriteria(@ParameterObject BusinessSubSegmentCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<BusinessSubSegmentDTO> page = businessSubSegmentCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<BusinessSubSegmentDTO>> getAllBusinessSubSegment(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all business sub segment , page: {}", pageable.getPageNumber());
        Page<BusinessSubSegmentDTO> page = businessSubSegmentService.getAllBusinessSubSegment(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<BusinessSubSegmentDTO> saveBusinessSubSegment(
            @Valid @RequestBody BusinessSubSegmentDTO dto) {
        log.info("Creating new business sub segment");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new business sub segment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusinessSubSegmentDTO saved = businessSubSegmentService.saveBusinessSubSegment(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessSubSegmentDTO> getBusinessSubSegmentById(@PathVariable Long id) {
        log.info("Fetching business sub segment with ID: {}", id);
        return businessSubSegmentService.getBusinessSubSegmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("business sub segment not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessSubSegmentDTO> updateBusinessSubSegment(
            @PathVariable Long id,
            @Valid @RequestBody BusinessSubSegmentDTO dto) {
        log.info("Updating business sub segment with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        BusinessSubSegmentDTO updated = businessSubSegmentService.updateBusinessSubSegment(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBusinessSubSegmentById(@PathVariable Long id) {
        log.info("Deleting business sub segment with ID: {}", id);
        boolean deleted = businessSubSegmentService.deleteBusinessSubSegmentById(id);
        if (deleted) {
            return ResponseEntity.ok("BusinessSubSegment deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("BusinessSubSegment deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteBusinessSubSegmentById(@PathVariable Long id) {
        log.info("Soft deleting business sub segment with ID: {}", id);

        boolean deleted = businessSubSegmentService.softDeleteBusinessSubSegmentById(id);
        if (deleted) {
            return ResponseEntity.ok("BusinessSubSegment soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("BusinessSubSegment soft deletion failed - ID: " + id);
        }
    }
}
