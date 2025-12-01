package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.ScheduledJobCriteria;
import com.cdl.escrow.dto.ScheduledJobDTO;
import com.cdl.escrow.entity.ScheduledJob;
import com.cdl.escrow.entity.StandingInstruction;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.ScheduledJobMapper;
import com.cdl.escrow.repository.ScheduledJobRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
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
public class ScheduledJobCriteriaService extends BaseSpecificationBuilder<ScheduledJob> implements Serializable {

    private final transient ScheduledJobRepository scheduledJobRepository;

    private final transient ScheduledJobMapper scheduledJobMapper;

    public Page<ScheduledJobDTO> findByCriteria(ScheduledJobCriteria criteria, Pageable pageable) {
        Specification<ScheduledJob> specification = createSpecification(criteria);
        return scheduledJobRepository.findAll(specification, pageable).map(scheduledJobMapper::toDto);
    }

    private Specification<ScheduledJob> createSpecification(ScheduledJobCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addIntegerFilter(cb, root, predicates, "executionSequenceNumber", criteria.getExecutionSequenceNumber());
                addStringFilter(cb, root, predicates, "scheduledJobIdentifier", criteria.getScheduledJobIdentifier(), true);
                addZonedDateTimeFilter(cb, root, predicates, "scheduledExecutionDateTime", criteria.getScheduledExecutionDateTime());
                addStringFilter(cb, root, predicates, "jobStatusCode", criteria.getJobStatusCode(),true);
                addStringFilter(cb, root, predicates, "triggeredBy", criteria.getTriggeredBy(),true);

                addZonedDateTimeFilter(cb, root, predicates, "completedDateTime", criteria.getCompletedDateTime());
                addStringFilter(cb, root, predicates, "remarks", criteria.getRemarks(),true);
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());

                // relationships

                if (criteria.getStandingInstructionId() != null) {
                    Join<ScheduledJob, StandingInstruction> join = root.join("standingInstruction", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getStandingInstructionId());
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
