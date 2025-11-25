package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.AccountTypeCategoryCriteria;
import com.cdl.escrow.criteriaservice.AccountTypeCategoryCriteriaService;
import com.cdl.escrow.dto.AccountTypeCategoryDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.AccountTypeCategoryRepository;
import com.cdl.escrow.service.AccountTypeCategoryService;
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
@RequestMapping("/api/v1/account-type-category")
@RequiredArgsConstructor
@Slf4j
public class AccountTypeCategoryController {

    private final AccountTypeCategoryService accountTypeCategoryService;

    private final AccountTypeCategoryCriteriaService accountTypeCategoryCriteriaService;

    private final AccountTypeCategoryRepository repository;

    private static final String ENTITY_NAME = "ACCOUNT-TYPE-CATEGORY";

   @GetMapping
    public ResponseEntity<Page<AccountTypeCategoryDTO>> getAllAccountTypeCategoryByCriteria(@ParameterObject AccountTypeCategoryCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountTypeCategoryDTO> page = accountTypeCategoryCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<AccountTypeCategoryDTO>> getAllAccountTypeCategory(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all account type category, page: {}", pageable.getPageNumber());
        Page<AccountTypeCategoryDTO> page = accountTypeCategoryService.getAllAccountTypeCategory(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<AccountTypeCategoryDTO> saveAccountTypeCategory(
            @Valid @RequestBody AccountTypeCategoryDTO dto) {
        log.info("Creating new account type category");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new account type category cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountTypeCategoryDTO saved = accountTypeCategoryService.saveAccountTypeCategory(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountTypeCategoryDTO> getAccountTypeCategoryById(@PathVariable Long id) {
        log.info("Fetching ascount type category with ID: {}", id);
        return accountTypeCategoryService.getAccountTypeCategoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Account type category not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountTypeCategoryDTO> updateAccountTypeCategory(
            @PathVariable Long id,
            @Valid @RequestBody AccountTypeCategoryDTO dto) {
        log.info("Updating account type category with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AccountTypeCategoryDTO updated = accountTypeCategoryService.updateAccountTypeCategory(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountTypeCategoryById(@PathVariable Long id) {
        log.info("Deleting account type category with ID: {}", id);
        boolean deleted = accountTypeCategoryService.deleteAccountTypeCategoryById(id);
        if (deleted) {
            return ResponseEntity.ok("AccountTypeCategory deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AccountTypeCategory deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteAccountTypeCategoryById(@PathVariable Long id) {
        log.info("Soft deleting account type category with ID: {}", id);

        boolean deleted = accountTypeCategoryService.softDeleteAccountTypeCategoryById(id);
        if (deleted) {
            return ResponseEntity.ok("AccountTypeCategory soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AccountTypeCategory soft deletion failed - ID: " + id);
        }
    }

}
