package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.PaymentInstructionCriteriaService;
import com.cdl.escrow.dto.PaymentInstructionDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.PaymentInstructionRepository;
import com.cdl.escrow.service.PaymentInstructionService;
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
@RequestMapping("/api/v1/payment-instruction")
@RequiredArgsConstructor
@Slf4j
public class PaymentInstructionController {

    private final PaymentInstructionService paymentInstructionService;

   // private final PaymentInstructionCriteriaService paymentInstructionCriteriaService;

    private final PaymentInstructionRepository repository;

    private static final String ENTITY_NAME = "PAYMENT-INSTRUCTION";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<PaymentInstructionDTO>> getAllPaymentInstruction(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all payment instruction , page: {}", pageable.getPageNumber());
        Page<PaymentInstructionDTO> page = paymentInstructionService.getAllPaymentInstruction(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<PaymentInstructionDTO> savePaymentInstruction(
            @Valid @RequestBody PaymentInstructionDTO dto) {
        log.info("Creating new payment instruction ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new payment instruction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymentInstructionDTO saved = paymentInstructionService.savePaymentInstruction(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentInstructionDTO> updatePaymentInstruction(@PathVariable Long id) {
        log.info("Fetching payment instruction with ID: {}", id);
        return paymentInstructionService.getPaymentInstructionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Payment instruction  not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentInstructionDTO> updatePaymentInstruction(
            @PathVariable Long id,
            @Valid @RequestBody PaymentInstructionDTO dto) {
        log.info("Updating payment instruction with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        PaymentInstructionDTO updated = paymentInstructionService.updatePaymentInstruction(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaymentInstructionById(@PathVariable Long id) {
        log.info("Deleting payment instruction with ID: {}", id);
        boolean deleted = paymentInstructionService.deletePaymentInstructionById(id);
        if (deleted) {
            return ResponseEntity.ok("PaymentInstruction deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("PaymentInstruction deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeletePaymentInstructionById(@PathVariable Long id) {
        log.info("Soft deleting payment instruction with ID: {}", id);

        boolean deleted = paymentInstructionService.softDeletePaymentInstructionById(id);
        if (deleted) {
            return ResponseEntity.ok("PaymentInstruction soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("PaymentInstruction soft deletion failed - ID: " + id);
        }
    }
}
