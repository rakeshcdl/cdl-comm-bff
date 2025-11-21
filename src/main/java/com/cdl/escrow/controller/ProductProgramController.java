package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.ProductProgramCriteriaService;
import com.cdl.escrow.dto.ProductProgramDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.ProductProgramRepository;
import com.cdl.escrow.service.ProductProgramService;
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
@RequestMapping("/api/v1/product-program")
@RequiredArgsConstructor
@Slf4j
public class ProductProgramController {

    private final ProductProgramService productProgramService;

    //private final ProductProgramCriteriaService productProgramCriteriaService;

    private final ProductProgramRepository repository;

    private static final String ENTITY_NAME = "PRODUCT-PROGRAM";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<ProductProgramDTO>> getAllProductProgram(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all product program , page: {}", pageable.getPageNumber());
        Page<ProductProgramDTO> page = productProgramService.getAllProductProgram(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<ProductProgramDTO> saveProductProgram(
            @Valid @RequestBody ProductProgramDTO dto) {
        log.info("Creating new product program  ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new product program  cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductProgramDTO saved = productProgramService.saveProductProgram(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductProgramDTO> getProductProgramById(@PathVariable Long id) {
        log.info("Fetching product program  with ID: {}", id);
        return productProgramService.getProductProgramById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Product program  not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductProgramDTO> updateProductProgram(
            @PathVariable Long id,
            @Valid @RequestBody ProductProgramDTO dto) {
        log.info("Updating product program  with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        ProductProgramDTO updated = productProgramService.updateProductProgram(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductProgramById(@PathVariable Long id) {
        log.info("Deleting product program  with ID: {}", id);
        boolean deleted = productProgramService.deleteProductProgramById(id);
        if (deleted) {
            return ResponseEntity.ok("ProductProgram deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ProductProgram deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteProductProgramById(@PathVariable Long id) {
        log.info("Soft deleting product program  with ID: {}", id);

        boolean deleted = productProgramService.softDeleteProductProgramById(id);
        if (deleted) {
            return ResponseEntity.ok("ProductProgram soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ProductProgram soft deletion failed - ID: " + id);
        }
    }
}
