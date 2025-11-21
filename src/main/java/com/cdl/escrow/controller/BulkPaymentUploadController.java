package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.BulkPaymentUploadCriteriaService;
import com.cdl.escrow.dto.BulkPaymentUploadDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.BulkPaymentUploadRepository;
import com.cdl.escrow.service.BulkPaymentUploadService;
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
@RequestMapping("/api/v1/bulk-payment-upload")
@RequiredArgsConstructor
@Slf4j
public class BulkPaymentUploadController {

    private final BulkPaymentUploadService bulkPaymentUploadService;

  //  private final BulkPaymentUploadCriteriaService bulkPaymentUploadCriteriaService;

    private final BulkPaymentUploadRepository repository;

    private static final String ENTITY_NAME = "BULK-PAYMENT-UPLOAD";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<BulkPaymentUploadDTO>> getAllBulkPaymentUpload(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all bulk payment upload , page: {}", pageable.getPageNumber());
        Page<BulkPaymentUploadDTO> page = bulkPaymentUploadService.getAllBulkPaymentUpload(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<BulkPaymentUploadDTO> saveBulkPaymentUpload(
            @Valid @RequestBody BulkPaymentUploadDTO dto) {
        log.info("Creating new bulk payment upload ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new bulk payment upload   cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BulkPaymentUploadDTO saved = bulkPaymentUploadService.saveBulkPaymentUpload(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BulkPaymentUploadDTO> getBulkPaymentUploadById(@PathVariable Long id) {
        log.info("Fetching bulk payment upload  with ID: {}", id);
        return bulkPaymentUploadService.getBulkPaymentUploadById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("bulk payment upload  not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<BulkPaymentUploadDTO> updateBulkPaymentUpload(
            @PathVariable Long id,
            @Valid @RequestBody BulkPaymentUploadDTO dto) {
        log.info("Updating bulk payment upload  with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        BulkPaymentUploadDTO updated = bulkPaymentUploadService.updateBulkPaymentUpload(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBulkPaymentUploadById(@PathVariable Long id) {
        log.info("Deleting bulk payment upload with ID: {}", id);
        boolean deleted = bulkPaymentUploadService.deleteBulkPaymentUploadById(id);
        if (deleted) {
            return ResponseEntity.ok("BulkPaymentUpload deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("BulkPaymentUpload deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteBulkPaymentUploadById(@PathVariable Long id) {
        log.info("Soft deleting bulk payment upload  with ID: {}", id);

        boolean deleted = bulkPaymentUploadService.softDeleteBulkPaymentUploadById(id);
        if (deleted) {
            return ResponseEntity.ok("BulkPaymentUpload soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("BulkPaymentUpload soft deletion failed - ID: " + id);
        }
    }
}
