package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.AgreementSubTypeCriteriaService;
import com.cdl.escrow.dto.AgreementSubTypeDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.AgreementSubTypeRepository;
import com.cdl.escrow.service.AgreementSubTypeService;
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
@RequestMapping("/api/v1/agreement-sub-type")
@RequiredArgsConstructor
@Slf4j
public class AgreementSubTypeController {

    private final AgreementSubTypeService agreementSubTypeService;

   // private final AgreementSubTypeCriteriaService agreementSubTypeCriteriaService;

    private final AgreementSubTypeRepository repository;

    private static final String ENTITY_NAME = "AGREEMENT-SUB-TYPE";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<AgreementSubTypeDTO>> getAllAgreementSubType(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all agreement sub type , page: {}", pageable.getPageNumber());
        Page<AgreementSubTypeDTO> page = agreementSubTypeService.getAllAgreementSubType(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<AgreementSubTypeDTO> saveAgreementSubType(
            @Valid @RequestBody AgreementSubTypeDTO dto) {
        log.info("Creating new agreement sub type ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new agreement sub type  cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgreementSubTypeDTO saved = agreementSubTypeService.saveAgreementSubType(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgreementSubTypeDTO> getAgreementSubTypeById(@PathVariable Long id) {
        log.info("Fetching agreement sub type  with ID: {}", id);
        return agreementSubTypeService.getAgreementSubTypeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("agreement sub type not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgreementSubTypeDTO> updateAgreementSubType(
            @PathVariable Long id,
            @Valid @RequestBody AgreementSubTypeDTO dto) {
        log.info("Updating agreement sub type with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AgreementSubTypeDTO updated = agreementSubTypeService.updateAgreementSubType(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgreementSubTypeById(@PathVariable Long id) {
        log.info("Deleting agreement sub type with ID: {}", id);
        boolean deleted = agreementSubTypeService.deleteAgreementSubTypeById(id);
        if (deleted) {
            return ResponseEntity.ok("AgreementSubType deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AgreementSubType deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteAgreementSubTypeById(@PathVariable Long id) {
        log.info("Soft deleting agreement sub type with ID: {}", id);

        boolean deleted = agreementSubTypeService.softDeleteAgreementSubTypeById(id);
        if (deleted) {
            return ResponseEntity.ok("AgreementSubType soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AgreementSubType soft deletion failed - ID: " + id);
        }
    }
}
