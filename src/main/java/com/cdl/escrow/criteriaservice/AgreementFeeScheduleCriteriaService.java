package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.AgreementFeeScheduleCriteria;
import com.cdl.escrow.dto.AgreementFeeScheduleDTO;
import com.cdl.escrow.entity.*;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.AgreementFeeScheduleMapper;
import com.cdl.escrow.repository.AgreementFeeScheduleRepository;
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
public class AgreementFeeScheduleCriteriaService extends BaseSpecificationBuilder<AgreementFeeSchedule> implements Serializable {

    private final transient AgreementFeeScheduleRepository agreementFeeScheduleRepository;

    private final transient AgreementFeeScheduleMapper agreementFeeScheduleMapper;

    public Page<AgreementFeeScheduleDTO> findByCriteria(AgreementFeeScheduleCriteria criteria, Pageable pageable) {
        Specification<AgreementFeeSchedule> specification = createSpecification(criteria);
        return agreementFeeScheduleRepository.findAll(specification, pageable).map(agreementFeeScheduleMapper::toDto);
    }

    private Specification<AgreementFeeSchedule> createSpecification(AgreementFeeScheduleCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "UUID", criteria.getUUID(), true);
                addZonedDateTimeFilter(cb, root, predicates, "effectiveStartDate", criteria.getEffectiveStartDate());
                addZonedDateTimeFilter(cb, root, predicates, "effectiveEndDate", criteria.getEffectiveEndDate());
                addStringFilter(cb, root, predicates, "operatingLocation", criteria.getOperatingLocation(),true);
                addStringFilter(cb, root, predicates, "priorityLevel", criteria.getPriorityLevel(),true);

                addStringFilter(cb, root, predicates, "transactionRateAmount", criteria.getTransactionRateAmount(), true);
                addStringFilter(cb, root, predicates, "debitAccountNumber", criteria.getDebitAccountNumber(), true);
                addStringFilter(cb, root, predicates, "creditAccountNumber", criteria.getCreditAccountNumber(), true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());

                // relationships

                if (criteria.getFeeId() != null) {
                    Join<AgreementFeeSchedule, ApplicationSetting> join = root.join("fee", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getFeeId());
                }

                if (criteria.getFeeTypeId() != null) {
                    Join<AgreementFeeSchedule, ApplicationSetting> join = root.join("feeType", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getFeeTypeId());
                }

                if (criteria.getFeesFrequencyId() != null) {
                    Join<AgreementFeeSchedule, ApplicationSetting> join = root.join("feesFrequency", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getFeesFrequencyId());
                }

                if (criteria.getFrequencyBasisId() != null) {
                    Join<AgreementFeeSchedule, ApplicationSetting> join = root.join("frequencyBasis", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getFrequencyBasisId());
                }

                if (criteria.getAgreementTypeId() != null) {
                    Join<AgreementFeeSchedule, AgreementType> join = root.join("agreementType", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getAgreementTypeId());
                }

                if (criteria.getAgreementSubTypeId() != null) {
                    Join<AgreementFeeSchedule, AgreementSubType> join = root.join("agreementSubType", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getAgreementSubTypeId());
                }

                if (criteria.getProductProgramId() != null) {
                    Join<AgreementFeeSchedule, ProductProgram> join = root.join("productProgram", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getProductProgramId());
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
