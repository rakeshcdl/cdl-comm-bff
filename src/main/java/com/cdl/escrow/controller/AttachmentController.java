package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.AttachmentCriteriaService;
import com.cdl.escrow.dto.AttachmentDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.AttachmentRepository;
import com.cdl.escrow.service.AttachmentService;
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
@RequestMapping("/api/v1/attachment")
@RequiredArgsConstructor
@Slf4j
public class AttachmentController {

    private final AttachmentService attachmentService;

    //private final AttachmentCriteriaService attachmentCriteriaService;

    private final AttachmentRepository repository;

    private static final String ENTITY_NAME = "ATTACHMENT";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<AttachmentDTO>> getAllAttachment(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all attachment , page: {}", pageable.getPageNumber());
        Page<AttachmentDTO> page = attachmentService.getAllAttachment(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<AttachmentDTO> saveAttachment(
            @Valid @RequestBody AttachmentDTO dto) {
        log.info("Creating new attachment ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new attachment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttachmentDTO saved = attachmentService.saveAttachment(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttachmentDTO> getAttachmentById(@PathVariable Long id) {
        log.info("Fetching attachment with ID: {}", id);
        return attachmentService.getAttachmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Attachment not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttachmentDTO> updateAttachment(
            @PathVariable Long id,
            @Valid @RequestBody AttachmentDTO dto) {
        log.info("Updating attachment with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AttachmentDTO updated = attachmentService.updateAttachment(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttachmentById(@PathVariable Long id) {
        log.info("Deleting attachment with ID: {}", id);
        boolean deleted = attachmentService.deleteAttachmentById(id);
        if (deleted) {
            return ResponseEntity.ok("Attachment deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Attachment deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteAttachmentById(@PathVariable Long id) {
        log.info("Soft deleting attachment with ID: {}", id);

        boolean deleted = attachmentService.softDeleteAttachmentById(id);
        if (deleted) {
            return ResponseEntity.ok("Attachment soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Attachment soft deletion failed - ID: " + id);
        }
    }
}
