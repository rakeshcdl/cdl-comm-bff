package com.cdl.escrow.controller;

import com.cdl.escrow.dto.AuthorizedSignatoryDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.AuthorizedSignatoryRepository;
import com.cdl.escrow.service.AuthorizedSignatoryService;
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
@RequestMapping("/api/v1/authorised-signatory")
@RequiredArgsConstructor
@Slf4j
public class AuthorizedSignatoryController {

    private final AuthorizedSignatoryService authorizedSignatoryService;

    //private final AuthorizedSignatoryCriteriaService authorizedSignatoryCriteriaService;

    private final AuthorizedSignatoryRepository repository;

    private static final String ENTITY_NAME = "AUTHORISED-SIGNATORY";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<AuthorizedSignatoryDTO>> getAllAuthorizedSignatory(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all authorised signatory , page: {}", pageable.getPageNumber());
        Page<AuthorizedSignatoryDTO> page = authorizedSignatoryService.getAllAuthorizedSignatory(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<AuthorizedSignatoryDTO> saveAuthorizedSignatory(
            @Valid @RequestBody AuthorizedSignatoryDTO dto) {
        log.info("Creating new authorised signatory ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new authorised signatory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuthorizedSignatoryDTO saved = authorizedSignatoryService.saveAuthorizedSignatory(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorizedSignatoryDTO> getAuthorizedSignatoryById(@PathVariable Long id) {
        log.info("Fetching authorised signatory with ID: {}", id);
        return authorizedSignatoryService.getAuthorizedSignatoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("authorised signatory not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorizedSignatoryDTO> updateAuthorizedSignatory(
            @PathVariable Long id,
            @Valid @RequestBody AuthorizedSignatoryDTO dto) {
        log.info("Updating authorised signatory with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AuthorizedSignatoryDTO updated = authorizedSignatoryService.updateAuthorizedSignatory(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthorizedSignatoryById(@PathVariable Long id) {
        log.info("Deleting authorised signatory with ID: {}", id);
        boolean deleted = authorizedSignatoryService.deleteAuthorizedSignatoryById(id);
        if (deleted) {
            return ResponseEntity.ok("AuthorizedSignatory deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AuthorizedSignatory deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteAuthorizedSignatoryById(@PathVariable Long id) {
        log.info("Soft deleting authorised signatory with ID: {}", id);

        boolean deleted = authorizedSignatoryService.softDeleteAuthorizedSignatoryById(id);
        if (deleted) {
            return ResponseEntity.ok("AuthorizedSignatory soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AuthorizedSignatory soft deletion failed - ID: " + id);
        }
    }
}
