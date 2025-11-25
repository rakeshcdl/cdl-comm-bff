package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.BusinessSegmentCriteria;
import com.cdl.escrow.criteriaservice.BusinessSegmentCriteriaService;
import com.cdl.escrow.dto.BusinessSegmentDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.BusinessSegmentRepository;
import com.cdl.escrow.service.BusinessSegmentService;
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
@RequestMapping("/api/v1/business-segment")
@RequiredArgsConstructor
@Slf4j
public class BusinessSegmentController {

    private final BusinessSegmentService businessSegmentService;

    private final BusinessSegmentCriteriaService businessSegmentCriteriaService;

    private final BusinessSegmentRepository repository;

    private static final String ENTITY_NAME = "BUSINESS-SEGMENT";

    @GetMapping
    public ResponseEntity<Page<BusinessSegmentDTO>> getAllBusinessSegmentCriteria(@ParameterObject BusinessSegmentCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<BusinessSegmentDTO> page = businessSegmentCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<BusinessSegmentDTO>> getAllBusinessSegment(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all business segment, page: {}", pageable.getPageNumber());
        Page<BusinessSegmentDTO> page = businessSegmentService.getAllBusinessSegment(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<BusinessSegmentDTO> saveBusinessSegment(
            @Valid @RequestBody BusinessSegmentDTO dto) {
        log.info("Creating new business segment ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new business segment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusinessSegmentDTO saved = businessSegmentService.saveBusinessSegment(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessSegmentDTO> getBusinessSegmentById(@PathVariable Long id) {
        log.info("Fetching business segment with ID: {}", id);
        return businessSegmentService.getBusinessSegmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("business segment not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessSegmentDTO> updateBusinessSegment(
            @PathVariable Long id,
            @Valid @RequestBody BusinessSegmentDTO dto) {
        log.info("Updating business segment with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        BusinessSegmentDTO updated = businessSegmentService.updateBusinessSegment(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBusinessSegmentById(@PathVariable Long id) {
        log.info("Deleting business segment with ID: {}", id);
        boolean deleted = businessSegmentService.deleteBusinessSegmentById(id);
        if (deleted) {
            return ResponseEntity.ok("BusinessSegment deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("BusinessSegment deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteBusinessSegmentById(@PathVariable Long id) {
        log.info("Soft deleting business segment with ID: {}", id);

        boolean deleted = businessSegmentService.softDeleteBusinessSegmentById(id);
        if (deleted) {
            return ResponseEntity.ok("BusinessSegment soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("BusinessSegment soft deletion failed - ID: " + id);
        }
    }
}
