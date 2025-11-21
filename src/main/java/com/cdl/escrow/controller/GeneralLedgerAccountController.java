package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.GeneralLedgerAccountCriteriaService;
import com.cdl.escrow.dto.GeneralLedgerAccountDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.GeneralLedgerAccountRepository;
import com.cdl.escrow.service.GeneralLedgerAccountService;
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
@RequestMapping("/api/v1/general-ledger-account")
@RequiredArgsConstructor
@Slf4j
public class GeneralLedgerAccountController {

    private final GeneralLedgerAccountService generalLedgerAccountService;

   // private final GeneralLedgerAccountCriteriaService generalLedgerAccountCriteriaService;

    private final GeneralLedgerAccountRepository repository;

    private static final String ENTITY_NAME = "GENERAL-LEDGER-ACCOUNT";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<GeneralLedgerAccountDTO>> getAllGeneralLedgerAccount(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all general ledger account , page: {}", pageable.getPageNumber());
        Page<GeneralLedgerAccountDTO> page = generalLedgerAccountService.getAllGeneralLedgerAccount(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<GeneralLedgerAccountDTO> saveGeneralLedgerAccount(
            @Valid @RequestBody GeneralLedgerAccountDTO dto) {
        log.info("Creating new general ledger account ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new general ledger account cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeneralLedgerAccountDTO saved = generalLedgerAccountService.saveGeneralLedgerAccount(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralLedgerAccountDTO> getGeneralLedgerAccountById(@PathVariable Long id) {
        log.info("Fetching general ledger account with ID: {}", id);
        return generalLedgerAccountService.getGeneralLedgerAccountById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("general ledger account not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralLedgerAccountDTO> updateGeneralLedgerAccount(
            @PathVariable Long id,
            @Valid @RequestBody GeneralLedgerAccountDTO dto) {
        log.info("Updating general ledger account with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        GeneralLedgerAccountDTO updated = generalLedgerAccountService.updateGeneralLedgerAccount(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGeneralLedgerAccountById(@PathVariable Long id) {
        log.info("Deleting general ledger account with ID: {}", id);
        boolean deleted = generalLedgerAccountService.deleteGeneralLedgerAccountById(id);
        if (deleted) {
            return ResponseEntity.ok("GeneralLedgerAccount deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("GeneralLedgerAccount deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteGeneralLedgerAccountById(@PathVariable Long id) {
        log.info("Soft deleting general ledger account with ID: {}", id);

        boolean deleted = generalLedgerAccountService.softDeleteGeneralLedgerAccountById(id);
        if (deleted) {
            return ResponseEntity.ok("GeneralLedgerAccount soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("GeneralLedgerAccount soft deletion failed - ID: " + id);
        }
    }
}
