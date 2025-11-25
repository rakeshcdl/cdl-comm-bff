package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.UnreconciledTransactionCriteria;
import com.cdl.escrow.criteriaservice.UnreconciledTransactionCriteriaService;
import com.cdl.escrow.dto.UnreconciledTransactionDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.UnreconciledTransactionRepository;
import com.cdl.escrow.service.UnreconciledTransactionService;
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
@RequestMapping("/api/v1/unreconciled-transaction")
@RequiredArgsConstructor
@Slf4j
public class UnreconciledTransactionController {

    private final UnreconciledTransactionService unreconciledTransactionService;

    private final UnreconciledTransactionCriteriaService unreconciledTransactionCriteriaService;

    private final UnreconciledTransactionRepository repository;

    private static final String ENTITY_NAME = "UNRECONCILED-TRANSACTION";

    @GetMapping
    public ResponseEntity<Page<UnreconciledTransactionDTO>> getAllUnreconciledTransactionCriteria(@ParameterObject UnreconciledTransactionCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<UnreconciledTransactionDTO> page = unreconciledTransactionCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<UnreconciledTransactionDTO>> getAllUnreconciledTransaction(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all unreconciled transaction , page: {}", pageable.getPageNumber());
        Page<UnreconciledTransactionDTO> page = unreconciledTransactionService.getAllUnreconciledTransaction(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<UnreconciledTransactionDTO> saveUnreconciledTransaction(
            @Valid @RequestBody UnreconciledTransactionDTO dto) {
        log.info("Creating new unreconciled transaction");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new unreconciled transaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UnreconciledTransactionDTO saved = unreconciledTransactionService.saveUnreconciledTransaction(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnreconciledTransactionDTO> getUnreconciledTransactionById(@PathVariable Long id) {
        log.info("Fetching unreconciled transaction  with ID: {}", id);
        return unreconciledTransactionService.getUnreconciledTransactionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Unreconciled transaction not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnreconciledTransactionDTO> updateUnreconciledTransaction(
            @PathVariable Long id,
            @Valid @RequestBody UnreconciledTransactionDTO dto) {
        log.info("Updating unreconciled transaction with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        UnreconciledTransactionDTO updated = unreconciledTransactionService.updateUnreconciledTransaction(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUnreconciledTransactionById(@PathVariable Long id) {
        log.info("Deleting unreconciled transaction with ID: {}", id);
        boolean deleted = unreconciledTransactionService.deleteUnreconciledTransactionById(id);
        if (deleted) {
            return ResponseEntity.ok("UnreconciledTransaction deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("UnreconciledTransaction deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteUnreconciledTransactionById(@PathVariable Long id) {
        log.info("Soft deleting unreconciled transaction with ID: {}", id);

        boolean deleted = unreconciledTransactionService.softDeleteUnreconciledTransactionById(id);
        if (deleted) {
            return ResponseEntity.ok("UnreconciledTransaction soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("UnreconciledTransaction soft deletion failed - ID: " + id);
        }
    }
}
