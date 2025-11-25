package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.StandingInstructionCriteria;
import com.cdl.escrow.dto.StandingInstructionDTO;
import com.cdl.escrow.entity.StandingInstruction;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.StandingInstructionMapper;
import com.cdl.escrow.repository.StandingInstructionRepository;
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
public class StandingInstructionCriteriaService extends BaseSpecificationBuilder<StandingInstruction> implements Serializable {

    private final transient StandingInstructionRepository standingInstructionRepository;

    private final transient StandingInstructionMapper standingInstructionMapper;

    public Page<StandingInstructionDTO> findByCriteria(StandingInstructionCriteria criteria, Pageable pageable) {
        Specification<StandingInstruction> specification = createSpecification(criteria);
        return standingInstructionRepository.findAll(specification, pageable).map(standingInstructionMapper::toDto);
    }

    private Specification<StandingInstruction> createSpecification(StandingInstructionCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "standingInstructionReferenceNumber", criteria.getStandingInstructionReferenceNumber(), true);
                addStringFilter(cb, root, predicates, "clientFullName", criteria.getClientFullName(), true);
                addBigDecimalFilter(cb, root, predicates, "debitAmountCap", criteria.getDebitAmountCap());

                addBigDecimalFilter(cb, root, predicates, "debitAmount", criteria.getDebitAmount());
                addBigDecimalFilter(cb, root, predicates, "minimumBalanceAmount", criteria.getMinimumBalanceAmount());
                addBigDecimalFilter(cb, root, predicates, "thresholdAmount", criteria.getThresholdAmount());
                addZonedDateTimeFilter(cb, root, predicates, "firstTransactionDateTime", criteria.getFirstTransactionDateTime());
                addZonedDateTimeFilter(cb, root, predicates, "instructionExpiryDateTime", criteria.getInstructionExpiryDateTime());
                addIntegerFilter(cb, root, predicates, "retryIntervalDays", criteria.getRetryIntervalDays());
                addBooleanFilter(cb, root, predicates, "retryUntilMonthEndFlag", criteria.getRetryUntilMonthEndFlag());
                addStringFilter(cb, root, predicates, "instructionRemarks", criteria.getInstructionRemarks(), true);
                addZonedDateTimeFilter(cb, root, predicates, "nextExecutionDateTime", criteria.getNextExecutionDateTime());

                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
