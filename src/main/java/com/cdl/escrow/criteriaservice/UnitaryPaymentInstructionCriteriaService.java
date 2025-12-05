package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.UnitaryPaymentInstructionCriteria;
import com.cdl.escrow.dto.UnitaryPaymentInstructionDTO;
import com.cdl.escrow.entity.*;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.UnitaryPaymentInstructionMapper;
import com.cdl.escrow.repository.UnitaryPaymentInstructionRepository;
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
public class UnitaryPaymentInstructionCriteriaService extends BaseSpecificationBuilder<UnitaryPaymentInstruction> implements Serializable {

    private final transient UnitaryPaymentInstructionRepository unitaryPaymentInstructionRepository;

    private final transient UnitaryPaymentInstructionMapper unitaryPaymentInstructionMapper;

    public Page<UnitaryPaymentInstructionDTO> findByCriteria(UnitaryPaymentInstructionCriteria criteria, Pageable pageable) {
        Specification<UnitaryPaymentInstruction> specification = createSpecification(criteria);
        return unitaryPaymentInstructionRepository.findAll(specification, pageable).map(unitaryPaymentInstructionMapper::toDto);
    }

    private Specification<UnitaryPaymentInstruction> createSpecification(UnitaryPaymentInstructionCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "paymentRefNo", criteria.getPaymentRefNo(), true);
                addStringFilter(cb, root, predicates, "customerId", criteria.getCustomerId(), true);
                addStringFilter(cb, root, predicates, "customerName", criteria.getCustomerName(),true);

                addStringFilter(cb, root, predicates, "beneficiaryId", criteria.getBeneficiaryId(),true);
                addStringFilter(cb, root, predicates, "beneficiaryName", criteria.getBeneficiaryName(),true);
                addStringFilter(cb, root, predicates, "bankName", criteria.getBankName(),true);
                addStringFilter(cb, root, predicates, "ifscCode", criteria.getIfscCode(),true);
                addStringFilter(cb, root, predicates, "bicCode", criteria.getBicCode(),true);
                addStringFilter(cb, root, predicates, "beneficiaryAccountNo", criteria.getBeneficiaryAccountNo(),true);
                addStringFilter(cb, root, predicates, "accountBalance", criteria.getAccountBalance(),true);
                addBigDecimalFilter(cb, root, predicates, "paymentAmount", criteria.getPaymentAmount());
                addStringFilter(cb, root, predicates, "otherRemarks", criteria.getOtherRemarks(),true);


                addZonedDateTimeFilter(cb, root, predicates, "paymentDate", criteria.getPaymentDate());
                addZonedDateTimeFilter(cb, root, predicates, "valueDate", criteria.getValueDate());
                addStringFilter(cb, root, predicates, "remarks", criteria.getRemarks(),true);
                addStringFilter(cb, root, predicates, "utrReferenceNumber", criteria.getUtrReferenceNumber(),true);
                addStringFilter(cb, root, predicates, "paymentResponseObj", criteria.getPaymentResponseObj(),true);
                addStringFilter(cb, root, predicates, "transactionDate", criteria.getTransactionDate(),true);
                addStringFilter(cb, root, predicates, "paymentStatus", criteria.getPaymentStatus(),true);

                addBooleanFilter(cb, root, predicates, "isTasPayment", criteria.getIsTasPayment());
                addBooleanFilter(cb, root, predicates, "isEnabled", criteria.getIsEnabled());


                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());

                // relationships

                if (criteria.getDealNoId() != null) {
                    Join<UnitaryPaymentInstruction, EscrowAgreement> join = root.join("dealNo", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getDealNoId());
                }
                if (criteria.getAccountNoId() != null) {
                    Join<UnitaryPaymentInstruction, EscrowAccount> join = root.join("accountNo", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getAccountNoId());
                }
                if (criteria.getPaymentTypeId() != null) {
                    Join<UnitaryPaymentInstruction, ApplicationSetting> join = root.join("paymentType", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getPaymentTypeId());
                }
                if (criteria.getAdhocPaymentId() != null) {
                    Join<UnitaryPaymentInstruction, ApplicationSetting> join = root.join("adhocPayment", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getAdhocPaymentId());
                }
                if (criteria.getPurposeId() != null) {
                    Join<UnitaryPaymentInstruction, ApplicationSetting> join = root.join("purpose", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getPurposeId());
                }
                if (criteria.getBeneficiaryCurrencyId() != null) {
                    Join<UnitaryPaymentInstruction, ApplicationSetting> join = root.join("beneficiaryCurrency", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getBeneficiaryCurrencyId());
                }

            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
