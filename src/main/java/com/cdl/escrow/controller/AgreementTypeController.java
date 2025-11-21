package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.AgreementTypeCriteriaService;
import com.cdl.escrow.dto.AgreementTypeDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.AgreementTypeRepository;
import com.cdl.escrow.service.AgreementTypeService;
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
@RequestMapping("/api/v1/agreement-type")
@RequiredArgsConstructor
@Slf4j
public class AgreementTypeController {

    private final AgreementTypeService agreementTypeService;

   // private final AgreementTypeCriteriaService agreementTypeCriteriaService;

    private final AgreementTypeRepository repository;

    private static final String ENTITY_NAME = "AGREEMENT-TYPE";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<AgreementTypeDTO>> getAllAgreementType(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all agreement type , page: {}", pageable.getPageNumber());
        Page<AgreementTypeDTO> page = agreementTypeService.getAllAgreementType(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<AgreementTypeDTO> saveAgreementType(
            @Valid @RequestBody AgreementTypeDTO dto) {
        log.info("Creating new agreement type ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new agreement type cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgreementTypeDTO saved = agreementTypeService.saveAgreementType(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgreementTypeDTO> getAgreementTypeById(@PathVariable Long id) {
        log.info("Fetching agreement type with ID: {}", id);
        return agreementTypeService.getAgreementTypeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("agreement type not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgreementTypeDTO> updateAgreementType(
            @PathVariable Long id,
            @Valid @RequestBody AgreementTypeDTO dto) {
        log.info("Updating agreement type with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AgreementTypeDTO updated = agreementTypeService.updateAgreementType(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgreementTypeById(@PathVariable Long id) {
        log.info("Deleting agreement type with ID: {}", id);
        boolean deleted = agreementTypeService.deleteAgreementTypeById(id);
        if (deleted) {
            return ResponseEntity.ok("AgreementType deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AgreementType deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteAgreementTypeById(@PathVariable Long id) {
        log.info("Soft deleting agreement type with ID: {}", id);

        boolean deleted = agreementTypeService.softDeleteAgreementTypeById(id);
        if (deleted) {
            return ResponseEntity.ok("AgreementType soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AgreementType soft deletion failed - ID: " + id);
        }
    }
}
