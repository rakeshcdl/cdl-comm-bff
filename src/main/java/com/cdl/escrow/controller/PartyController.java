package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.PartyCriteriaService;
import com.cdl.escrow.dto.PartyDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.PartyRepository;
import com.cdl.escrow.service.PartyService;
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
@RequestMapping("/api/v1/party")
@RequiredArgsConstructor
@Slf4j
public class PartyController {

    private final PartyService partyService;

    //private final PartyCriteriaService partyCriteriaService;

    private final PartyRepository repository;

    private static final String ENTITY_NAME = "PARTY";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<PartyDTO>> getAllParty(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all party , page: {}", pageable.getPageNumber());
        Page<PartyDTO> page = partyService.getAllParty(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<PartyDTO> saveParty(
            @Valid @RequestBody PartyDTO dto) {
        log.info("Creating new party ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new party cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PartyDTO saved = partyService.saveParty(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartyDTO> getPartyById(@PathVariable Long id) {
        log.info("Fetching party with ID: {}", id);
        return partyService.getPartyById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Party not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartyDTO> updateParty(
            @PathVariable Long id,
            @Valid @RequestBody PartyDTO dto) {
        log.info("Updating party with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        PartyDTO updated = partyService.updateParty(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePartyById(@PathVariable Long id) {
        log.info("Deleting party with ID: {}", id);
        boolean deleted = partyService.deletePartyById(id);
        if (deleted) {
            return ResponseEntity.ok("Party deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Party deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeletePartyById(@PathVariable Long id) {
        log.info("Soft deleting party with ID: {}", id);

        boolean deleted = partyService.softDeletePartyById(id);
        if (deleted) {
            return ResponseEntity.ok("Party soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Party soft deletion failed - ID: " + id);
        }
    }
}
