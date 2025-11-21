package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.AgreementSignatoryCriteriaService;
import com.cdl.escrow.dto.AgreementSignatoryDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.AgreementSignatoryRepository;
import com.cdl.escrow.service.AgreementSignatoryService;
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
@RequestMapping("/api/v1/agreement-signatory")
@RequiredArgsConstructor
@Slf4j
public class AgreementSignatoryController {

    private final AgreementSignatoryService agreementSignatoryService;

   // private final AgreementSignatoryCriteriaService agreementSignatoryCriteriaService;

    private final AgreementSignatoryRepository repository;

    private static final String ENTITY_NAME = "ACCOUNT-TYPE";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<AgreementSignatoryDTO>> getAllAgreementSignatory(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all agreement signatory , page: {}", pageable.getPageNumber());
        Page<AgreementSignatoryDTO> page = agreementSignatoryService.getAllAgreementSignatory(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<AgreementSignatoryDTO> saveAgreementSignatory(
            @Valid @RequestBody AgreementSignatoryDTO dto) {
        log.info("Creating new agreement signatory ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new agreement signatory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgreementSignatoryDTO saved = agreementSignatoryService.saveAgreementSignatory(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgreementSignatoryDTO> getAgreementSignatoryById(@PathVariable Long id) {
        log.info("Fetching agreement signatory with ID: {}", id);
        return agreementSignatoryService.getAgreementSignatoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Agreement signatory not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgreementSignatoryDTO> updateAgreementSignatory(
            @PathVariable Long id,
            @Valid @RequestBody AgreementSignatoryDTO dto) {
        log.info("Updating agreement signatory with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AgreementSignatoryDTO updated = agreementSignatoryService.updateAgreementSignatory(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgreementSignatoryById(@PathVariable Long id) {
        log.info("Deleting agreement signatory with ID: {}", id);
        boolean deleted = agreementSignatoryService.deleteAgreementSignatoryById(id);
        if (deleted) {
            return ResponseEntity.ok("AgreementSignatory deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AgreementSignatory deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteAgreementSignatoryById(@PathVariable Long id) {
        log.info("Soft deleting agreement signatory with ID: {}", id);

        boolean deleted = agreementSignatoryService.softDeleteAgreementSignatoryById(id);
        if (deleted) {
            return ResponseEntity.ok("AgreementSignatory soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AgreementSignatory soft deletion failed - ID: " + id);
        }
    }
}
