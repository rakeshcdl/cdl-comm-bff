package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.StandingInstructionBeneficiaryCriteria;
import com.cdl.escrow.dto.StandingInstructionBeneficiaryDTO;
import com.cdl.escrow.entity.*;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.StandingInstructionBeneficiaryMapper;
import com.cdl.escrow.repository.StandingInstructionBeneficiaryRepository;
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
public class StandingInstructionBeneficiaryCriteriaService extends BaseSpecificationBuilder<StandingInstructionBeneficiary> implements Serializable {

    private final transient StandingInstructionBeneficiaryRepository standingInstructionBeneficiaryRepository;

    private final transient StandingInstructionBeneficiaryMapper standingInstructionBeneficiaryMapper;

    public Page<StandingInstructionBeneficiaryDTO> findByCriteria(StandingInstructionBeneficiaryCriteria criteria, Pageable pageable) {
        Specification<StandingInstructionBeneficiary> specification = createSpecification(criteria);
        return standingInstructionBeneficiaryRepository.findAll(specification, pageable).map(standingInstructionBeneficiaryMapper::toDto);
    }

    private Specification<StandingInstructionBeneficiary> createSpecification(StandingInstructionBeneficiaryCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "beneficiaryAccountNumber", criteria.getBeneficiaryAccountNumber(), true);
                addStringFilter(cb, root, predicates, "beneficiaryBankIfscCode", criteria.getBeneficiaryBankIfscCode(), true);
                addBigDecimalFilter(cb, root, predicates, "creditAmountCap", criteria.getCreditAmountCap());
                addBigDecimalFilter(cb, root, predicates, "creditAmount", criteria.getCreditAmount());
                addIntegerFilter(cb, root, predicates, "transferPriorityLevel", criteria.getTransferPriorityLevel());
                addBigDecimalFilter(cb, root, predicates, "creditSharePercentage", criteria.getCreditSharePercentage());
                addStringFilter(cb, root, predicates, "currencyCode", criteria.getCurrencyCode(), true);
                addStringFilter(cb, root, predicates, "paymentModeCode", criteria.getPaymentModeCode(), true);
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());

                // relationships

                if (criteria.getBeneficiaryNameId() != null) {
                    Join<StandingInstructionBeneficiary, Beneficiary> join = root.join("beneficiaryName", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getBeneficiaryNameId());
                }

                if (criteria.getPaymentModeId() != null) {
                    Join<StandingInstructionBeneficiary, ApplicationSetting> join = root.join("paymentMode", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getStandingInstructionId());
                }

                if (criteria.getCurrencyId() != null) {
                    Join<StandingInstructionBeneficiary, ApplicationSetting> join = root.join("currency", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getCurrencyId());
                }

                if (criteria.getStandingInstructionId() != null) {
                    Join<StandingInstructionBeneficiary, StandingInstruction> join = root.join("standingInstruction", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getStandingInstructionId());
                }

            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
