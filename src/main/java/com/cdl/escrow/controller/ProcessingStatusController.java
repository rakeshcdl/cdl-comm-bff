package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.ProcessingStatusCriteriaService;
import com.cdl.escrow.dto.ProcessingStatusDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.ProcessingStatusRepository;
import com.cdl.escrow.service.ProcessingStatusService;
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
@RequestMapping("/api/v1/processing-status")
@RequiredArgsConstructor
@Slf4j
public class ProcessingStatusController {

    private final ProcessingStatusService processingStatusService;

    //private final ProcessingStatusCriteriaService processingStatusCriteriaService;

    private final ProcessingStatusRepository repository;

    private static final String ENTITY_NAME = "PROCESSING-STATUS";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<ProcessingStatusDTO>> getAllProcessingStatus(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all processing status , page: {}", pageable.getPageNumber());
        Page<ProcessingStatusDTO> page = processingStatusService.getAllProcessingStatus(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<ProcessingStatusDTO> saveProcessingStatus(
            @Valid @RequestBody ProcessingStatusDTO dto) {
        log.info("Creating new processing status ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new processing status cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProcessingStatusDTO saved = processingStatusService.saveProcessingStatus(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessingStatusDTO> getProcessingStatusById(@PathVariable Long id) {
        log.info("Fetching processing status  with ID: {}", id);
        return processingStatusService.getProcessingStatusById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("processing status  not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcessingStatusDTO> updateProcessingStatus(
            @PathVariable Long id,
            @Valid @RequestBody ProcessingStatusDTO dto) {
        log.info("Updating processing status  with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        ProcessingStatusDTO updated = processingStatusService.updateProcessingStatus(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProcessingStatusById(@PathVariable Long id) {
        log.info("Deleting processing status  with ID: {}", id);
        boolean deleted = processingStatusService.deleteProcessingStatusById(id);
        if (deleted) {
            return ResponseEntity.ok("ProcessingStatus deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ProcessingStatus deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteProcessingStatusById(@PathVariable Long id) {
        log.info("Soft deleting processing status  with ID: {}", id);

        boolean deleted = processingStatusService.softDeleteProcessingStatusById(id);
        if (deleted) {
            return ResponseEntity.ok("ProcessingStatus soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ProcessingStatus soft deletion failed - ID: " + id);
        }
    }
}
