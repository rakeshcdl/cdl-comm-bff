package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.ScheduledJobCriteria;
import com.cdl.escrow.criteriaservice.ScheduledJobCriteriaService;
import com.cdl.escrow.dto.ScheduledJobDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.ScheduledJobRepository;
import com.cdl.escrow.service.ScheduledJobService;
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
@RequestMapping("/api/v1/schedule-job")
@RequiredArgsConstructor
@Slf4j
public class ScheduledJobController {

    private final ScheduledJobService scheduledJobService;

    private final ScheduledJobCriteriaService scheduledJobCriteriaService;

    private final ScheduledJobRepository repository;

    private static final String ENTITY_NAME = "SCHEDULE-JOB";

    @GetMapping
    public ResponseEntity<Page<ScheduledJobDTO>> getAllScheduledJobCriteria(@ParameterObject ScheduledJobCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<ScheduledJobDTO> page = scheduledJobCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<ScheduledJobDTO>> getAllScheduledJob(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all schedule job , page: {}", pageable.getPageNumber());
        Page<ScheduledJobDTO> page = scheduledJobService.getAllScheduledJob(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<ScheduledJobDTO> saveScheduledJob(
            @Valid @RequestBody ScheduledJobDTO dto) {
        log.info("Creating new schedule job ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new schedule job cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScheduledJobDTO saved = scheduledJobService.saveScheduledJob(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduledJobDTO> getScheduledJobById(@PathVariable Long id) {
        log.info("Fetching schedule job  with ID: {}", id);
        return scheduledJobService.getScheduledJobById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("schedule job not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduledJobDTO> updateScheduledJob(
            @PathVariable Long id,
            @Valid @RequestBody ScheduledJobDTO dto) {
        log.info("Updating schedule job with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        ScheduledJobDTO updated = scheduledJobService.updateScheduledJob(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScheduledJobById(@PathVariable Long id) {
        log.info("Deleting schedule job with ID: {}", id);
        boolean deleted = scheduledJobService.deleteScheduledJobById(id);
        if (deleted) {
            return ResponseEntity.ok("ScheduledJob deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ScheduledJob deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteScheduledJobById(@PathVariable Long id) {
        log.info("Soft deleting schedule job with ID: {}", id);

        boolean deleted = scheduledJobService.softDeleteScheduledJobById(id);
        if (deleted) {
            return ResponseEntity.ok("ScheduledJob soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ScheduledJob soft deletion failed - ID: " + id);
        }
    }
}
