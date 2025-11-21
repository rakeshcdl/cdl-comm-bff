package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.BeneficiaryCriteriaService;
import com.cdl.escrow.dto.BeneficiaryDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.BeneficiaryRepository;
import com.cdl.escrow.service.BeneficiaryService;
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
@RequestMapping("/api/v1/beneficiary")
@RequiredArgsConstructor
@Slf4j
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

   // private final BeneficiaryCriteriaService beneficiaryCriteriaService;

    private final BeneficiaryRepository repository;

    private static final String ENTITY_NAME = "BENEFICIARY";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<BeneficiaryDTO>> getAllBeneficiary(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all beneficiary , page: {}", pageable.getPageNumber());
        Page<BeneficiaryDTO> page = beneficiaryService.getAllBeneficiary(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<BeneficiaryDTO> saveBeneficiary(
            @Valid @RequestBody BeneficiaryDTO dto) {
        log.info("Creating new beneficiary");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new beneficiary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BeneficiaryDTO saved = beneficiaryService.saveBeneficiary(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeneficiaryDTO> getBeneficiaryById(@PathVariable Long id) {
        log.info("Fetching beneficiary with ID: {}", id);
        return beneficiaryService.getBeneficiaryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("beneficiary not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeneficiaryDTO> updateBeneficiary(
            @PathVariable Long id,
            @Valid @RequestBody BeneficiaryDTO dto) {
        log.info("Updating beneficiary with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        BeneficiaryDTO updated = beneficiaryService.updateBeneficiary(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBeneficiaryById(@PathVariable Long id) {
        log.info("Deleting beneficiary with ID: {}", id);
        boolean deleted = beneficiaryService.deleteBeneficiaryById(id);
        if (deleted) {
            return ResponseEntity.ok("beneficiary deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("beneficiary deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteBeneficiaryById(@PathVariable Long id) {
        log.info("Soft deleting beneficiary with ID: {}", id);

        boolean deleted = beneficiaryService.softDeleteBeneficiaryById(id);
        if (deleted) {
            return ResponseEntity.ok("beneficiary soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("beneficiary soft deletion failed - ID: " + id);
        }
    }
}
