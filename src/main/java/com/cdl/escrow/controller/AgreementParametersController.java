package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.AgreementParametersCriteriaService;
import com.cdl.escrow.dto.AgreementParametersDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.AgreementParametersRepository;
import com.cdl.escrow.service.AgreementParametersService;
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
@RequestMapping("/api/v1/agreement-parameter")
@RequiredArgsConstructor
@Slf4j
public class AgreementParametersController {

    private final AgreementParametersService agreementParametersService;

    //private final AgreementParametersCriteriaService agreementParametersCriteriaService;

    private final AgreementParametersRepository repository;

    private static final String ENTITY_NAME = "AGREEMENT-PARAMETER";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<AgreementParametersDTO>> getAllAgreementParameters(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all agreement parameter , page: {}", pageable.getPageNumber());
        Page<AgreementParametersDTO> page = agreementParametersService.getAllAgreementParameters(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<AgreementParametersDTO> saveAgreementParameter(
            @Valid @RequestBody AgreementParametersDTO dto) {
        log.info("Creating new agreement parameter ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new agreement parameter  cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgreementParametersDTO saved = agreementParametersService.saveAgreementParameter(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgreementParametersDTO> getAllAgreementParameters(@PathVariable Long id) {
        log.info("Fetching agreement parameter  with ID: {}", id);
        return agreementParametersService.getAgreementParameterById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Agreement parameter not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgreementParametersDTO> updateAgreementParameter(
            @PathVariable Long id,
            @Valid @RequestBody AgreementParametersDTO dto) {
        log.info("Updating agreement parameter with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AgreementParametersDTO updated = agreementParametersService.updateAgreementParameter(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgreementParameterById(@PathVariable Long id) {
        log.info("Deleting agreement parameter with ID: {}", id);
        boolean deleted = agreementParametersService.deleteAgreementParameterById(id);
        if (deleted) {
            return ResponseEntity.ok("AgreementParameters deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AgreementParameters deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteAgreementParameterById(@PathVariable Long id) {
        log.info("Soft deleting agreement parameter with ID: {}", id);

        boolean deleted = agreementParametersService.softDeleteAgreementParameterById(id);
        if (deleted) {
            return ResponseEntity.ok("AgreementParameter soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AgreementParameter soft deletion failed - ID: " + id);
        }
    }
}
