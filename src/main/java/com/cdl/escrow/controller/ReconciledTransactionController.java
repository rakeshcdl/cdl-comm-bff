package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.ReconciledTransactionCriteriaService;
import com.cdl.escrow.dto.ReconciledTransactionDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.ReconciledTransactionRepository;
import com.cdl.escrow.service.ReconciledTransactionService;
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
@RequestMapping("/api/v1/reconciled-transaction")
@RequiredArgsConstructor
@Slf4j
public class ReconciledTransactionController {

    private final ReconciledTransactionService reconciledTransactionService;

   // private final ReconciledTransactionCriteriaService reconciledTransactionCriteriaService;

    private final ReconciledTransactionRepository repository;

    private static final String ENTITY_NAME = "RECONCILED-TRANSACTION";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<ReconciledTransactionDTO>> getAllReconciledTransaction(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all reconciled transaction , page: {}", pageable.getPageNumber());
        Page<ReconciledTransactionDTO> page = reconciledTransactionService.getAllReconciledTransaction(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<ReconciledTransactionDTO> saveReconciledTransaction(
            @Valid @RequestBody ReconciledTransactionDTO dto) {
        log.info("Creating new reconciled transaction ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new reconciled transaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReconciledTransactionDTO saved = reconciledTransactionService.saveReconciledTransaction(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReconciledTransactionDTO> getAllReconciledTransaction(@PathVariable Long id) {
        log.info("Fetching reconciled transaction with ID: {}", id);
        return reconciledTransactionService.getReconciledTransactionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Reconciled transaction not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReconciledTransactionDTO> updateReconciledTransaction(
            @PathVariable Long id,
            @Valid @RequestBody ReconciledTransactionDTO dto) {
        log.info("Updating reconciled transaction with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        ReconciledTransactionDTO updated = reconciledTransactionService.updateReconciledTransaction(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReconciledTransactionById(@PathVariable Long id) {
        log.info("Deleting reconciled transaction with ID: {}", id);
        boolean deleted = reconciledTransactionService.deleteReconciledTransactionById(id);
        if (deleted) {
            return ResponseEntity.ok("ReconciledTransaction deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ReconciledTransaction deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteAccountTypeById(@PathVariable Long id) {
        log.info("Soft deleting reconciled transaction with ID: {}", id);

        boolean deleted = reconciledTransactionService.softDeleteReconciledTransactionById(id);
        if (deleted) {
            return ResponseEntity.ok("ReconciledTransaction soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ReconciledTransaction soft deletion failed - ID: " + id);
        }
    }
}
