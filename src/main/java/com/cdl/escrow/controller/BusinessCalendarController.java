package com.cdl.escrow.controller;

import com.cdl.escrow.criteriaservice.BusinessCalendarCriteriaService;
import com.cdl.escrow.dto.BusinessCalendarDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.BusinessCalendarRepository;
import com.cdl.escrow.service.BusinessCalendarService;
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
@RequestMapping("/api/v1/business-calender")
@RequiredArgsConstructor
@Slf4j
public class BusinessCalendarController {

    private final BusinessCalendarService businessCalendarService;

    //private final BusinessCalendarCriteriaService businessCalendarCriteriaService;

    private final BusinessCalendarRepository repository;

    private static final String ENTITY_NAME = "BUSINESS-CALENDER";

 /*   @GetMapping
    public ResponseEntity<Page<AccountPurposeDTO>> getAllAccountPurpose(@ParameterObject AccountPurposeCriteria criteria,
                                                                                   @ParameterObject Pageable pageable) {
        Page<AccountPurposeDTO> page = accountPurposeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<BusinessCalendarDTO>> getAllBusinessCalendar(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all business calender , page: {}", pageable.getPageNumber());
        Page<BusinessCalendarDTO> page = businessCalendarService.getAllBusinessCalendar(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<BusinessCalendarDTO> saveBusinessCalendar(
            @Valid @RequestBody BusinessCalendarDTO dto) {
        log.info("Creating new business calender  ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new business calender  cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusinessCalendarDTO saved = businessCalendarService.saveBusinessCalendar(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessCalendarDTO> getBusinessCalendarById(@PathVariable Long id) {
        log.info("Fetching business calender  with ID: {}", id);
        return businessCalendarService.getBusinessCalendarById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("business calender  not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessCalendarDTO> updateBusinessCalendar(
            @PathVariable Long id,
            @Valid @RequestBody BusinessCalendarDTO dto) {
        log.info("Updating business calender  with ID: {}", id);

        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        BusinessCalendarDTO updated = businessCalendarService.updateBusinessCalendar(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBusinessCalendarById(@PathVariable Long id) {
        log.info("Deleting business calender  with ID: {}", id);
        boolean deleted = businessCalendarService.deleteBusinessCalendarById(id);
        if (deleted) {
            return ResponseEntity.ok("BusinessCalendar deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("BusinessCalendar deletion failed - ID: " + id);
        }
    }
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteBusinessCalendarById(@PathVariable Long id) {
        log.info("Soft deleting business calender  with ID: {}", id);

        boolean deleted = businessCalendarService.softDeleteBusinessCalendarById(id);
        if (deleted) {
            return ResponseEntity.ok("BusinessCalendar soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("BusinessCalendar soft deletion failed - ID: " + id);
        }
    }
}
