package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.PartyDocumentCriteriaService;
import com.cdl.escrow.dto.PartyDocumentDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.PartyDocumentRepository;
import com.cdl.escrow.service.PartyDocumentService;
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
@RequestMapping("/api/v1/party-document")
@RequiredArgsConstructor
@Slf4j
public class PartyDocumentController {

    private final PartyDocumentService partyDocumentService;

    //private final PartyDocumentCriteriaService partyDocumentCriteriaService;

    private final PartyDocumentRepository repository;

    private static final String ENTITY_NAME = "PARTY-DOCUMENT";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<PartyDocumentDTO>> getAllPartyDocument(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all party document , page: {}", pageable.getPageNumber());
        Page<PartyDocumentDTO> page = partyDocumentService.getAllPartyDocument(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<PartyDocumentDTO> savePartyDocument(
            @Valid @RequestBody PartyDocumentDTO dto) {
        log.info("Creating new party document ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new party document cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PartyDocumentDTO saved = partyDocumentService.savePartyDocument(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartyDocumentDTO> getPartyDocumentById(@PathVariable Long id) {
        log.info("Fetching party document with ID: {}", id);
        return partyDocumentService.getPartyDocumentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Party document not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartyDocumentDTO> updatePartyDocument(
            @PathVariable Long id,
            @Valid @RequestBody PartyDocumentDTO dto) {
        log.info("Updating party document with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        PartyDocumentDTO updated = partyDocumentService.updatePartyDocument(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePartyDocumentById(@PathVariable Long id) {
        log.info("Deleting party document with ID: {}", id);
        boolean deleted = partyDocumentService.deletePartyDocumentById(id);
        if (deleted) {
            return ResponseEntity.ok("PartyDocument deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("PartyDocument deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeletePartyDocumentById(@PathVariable Long id) {
        log.info("Soft deleting party document with ID: {}", id);

        boolean deleted = partyDocumentService.softDeletePartyDocumentById(id);
        if (deleted) {
            return ResponseEntity.ok("PartyDocument soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("PartyDocument soft deletion failed - ID: " + id);
        }
    }
}
