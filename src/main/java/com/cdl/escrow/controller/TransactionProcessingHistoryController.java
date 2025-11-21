package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.TransactionProcessingHistoryCriteriaService;
import com.cdl.escrow.dto.TransactionProcessingHistoryDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.TransactionProcessingHistoryRepository;
import com.cdl.escrow.service.TransactionProcessingHistoryService;
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
@RequestMapping("/api/v1/transaction-processing-history")
@RequiredArgsConstructor
@Slf4j
public class TransactionProcessingHistoryController {

    private final TransactionProcessingHistoryService transactionProcessingHistoryService;

    //private final TransactionProcessingHistoryCriteriaService transactionProcessingHistoryCriteriaService;

    private final TransactionProcessingHistoryRepository repository;

    private static final String ENTITY_NAME = "TRANSACTION-PROCESSING-HISTORY";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<TransactionProcessingHistoryDTO>> getAllTransactionProcessingHistory(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all transaction processing history , page: {}", pageable.getPageNumber());
        Page<TransactionProcessingHistoryDTO> page = transactionProcessingHistoryService.getAllTransactionProcessingHistory(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<TransactionProcessingHistoryDTO> saveTransactionProcessingHistory(
            @Valid @RequestBody TransactionProcessingHistoryDTO dto) {
        log.info("Creating new transaction processing history ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new transaction processing history cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransactionProcessingHistoryDTO saved = transactionProcessingHistoryService.saveTransactionProcessingHistory(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionProcessingHistoryDTO> getTransactionProcessingHistoryById(@PathVariable Long id) {
        log.info("Fetching transaction processing history with ID: {}", id);
        return transactionProcessingHistoryService.getTransactionProcessingHistoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("transaction processing history not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionProcessingHistoryDTO> updateTransactionProcessingHistory(
            @PathVariable Long id,
            @Valid @RequestBody TransactionProcessingHistoryDTO dto) {
        log.info("Updating transaction processing history with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        TransactionProcessingHistoryDTO updated = transactionProcessingHistoryService.updateTransactionProcessingHistory(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransactionProcessingHistoryById(@PathVariable Long id) {
        log.info("Deleting transaction processing history with ID: {}", id);
        boolean deleted = transactionProcessingHistoryService.deleteTransactionProcessingHistoryById(id);
        if (deleted) {
            return ResponseEntity.ok("TransactionProcessingHistory deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("TransactionProcessingHistory deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteTransactionProcessingHistoryById(@PathVariable Long id) {
        log.info("Soft deleting transaction processing history with ID: {}", id);

        boolean deleted = transactionProcessingHistoryService.softDeleteTransactionProcessingHistoryById(id);
        if (deleted) {
            return ResponseEntity.ok("TransactionProcessingHistory soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("TransactionProcessingHistory soft deletion failed - ID: " + id);
        }
    }
}
