package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.ProcessingStatusCriteria;
import com.cdl.escrow.dto.ProcessingStatusDTO;
import com.cdl.escrow.entity.ProcessingStatus;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.ProcessingStatusMapper;
import com.cdl.escrow.repository.ProcessingStatusRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessingStatusCriteriaService extends BaseSpecificationBuilder<ProcessingStatus> implements Serializable {

    private final transient ProcessingStatusRepository processingStatusRepository;

    private final transient ProcessingStatusMapper processingStatusMapper;

    public Page<ProcessingStatusDTO> findByCriteria(ProcessingStatusCriteria criteria, Pageable pageable) {
        Specification<ProcessingStatus> specification = createSpecification(criteria);
        return processingStatusRepository.findAll(specification, pageable).map(processingStatusMapper::toDto);
    }

    private Specification<ProcessingStatus> createSpecification(ProcessingStatusCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addLongFilter(cb, root, predicates, "previousJobReferenceId", criteria.getPreviousJobReferenceId());
                addLongFilter(cb, root, predicates, "currentJobReferenceId", criteria.getCurrentJobReferenceId());
                addLongFilter(cb, root, predicates, "nextJobReferenceId", criteria.getNextJobReferenceId());
                addStringFilter(cb, root, predicates, "processDetails", criteria.getProcessDetails(),true);
                addStringFilter(cb, root, predicates, "statusMessage", criteria.getStatusMessage(),true);

                addZonedDateTimeFilter(cb, root, predicates, "createdDateTime", criteria.getCreatedDateTime());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
