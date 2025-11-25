package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.PaymentInstructionCriteria;
import com.cdl.escrow.dto.PaymentInstructionDTO;
import com.cdl.escrow.entity.ApplicationSetting;
import com.cdl.escrow.entity.PaymentInstruction;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.PaymentInstructionMapper;
import com.cdl.escrow.repository.PaymentInstructionRepository;
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
public class PaymentInstructionCriteriaService extends BaseSpecificationBuilder<PaymentInstruction> implements Serializable {

    private final transient PaymentInstructionRepository paymentInstructionRepository;

    private final transient PaymentInstructionMapper paymentInstructionMapper;

    public Page<PaymentInstructionDTO> findByCriteria(PaymentInstructionCriteria criteria, Pageable pageable) {
        Specification<PaymentInstruction> specification = createSpecification(criteria);
        return paymentInstructionRepository.findAll(specification, pageable).map(paymentInstructionMapper::toDto);
    }

    private Specification<PaymentInstruction> createSpecification(PaymentInstructionCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "paymentReferenceNumber", criteria.getPaymentReferenceNumber(), true);
                addStringFilter(cb, root, predicates, "payerCustomerId", criteria.getPayerCustomerId(), true);
                addStringFilter(cb, root, predicates, "payerFullName", criteria.getPayerFullName(), true);

                addStringFilter(cb, root, predicates, "beneficiaryId", criteria.getBeneficiaryId(), true);
                addStringFilter(cb, root, predicates, "beneficiaryFullName", criteria.getPayerFullName(), true);
                addStringFilter(cb, root, predicates, "beneficiaryBankName", criteria.getBeneficiaryBankName(), true);
                addStringFilter(cb, root, predicates, "beneficiaryBankIfscCode", criteria.getBeneficiaryBankIfscCode(), true);
                addStringFilter(cb, root, predicates, "beneficiaryBankBic", criteria.getBeneficiaryBankBic(), true);
                addStringFilter(cb, root, predicates, "beneficiaryAccountNumber", criteria.getBeneficiaryAccountNumber(), true);
                addStringFilter(cb, root, predicates, "accountBalanceAmount", criteria.getAccountBalanceAmount(), true);
                addDoubleFilter(cb, root, predicates, "paymentAmount", criteria.getPaymentAmount());
                addStringFilter(cb, root, predicates, "transactionRemarks", criteria.getTransactionRemarks(), true);

                addZonedDateTimeFilter(cb, root, predicates, "paymentInitiationDateTime", criteria.getPaymentInitiationDateTime());
                addZonedDateTimeFilter(cb, root, predicates, "valueDateTime", criteria.getValueDateTime());
                addStringFilter(cb, root, predicates, "additionalRemarks", criteria.getAdditionalRemarks(), true);
                addStringFilter(cb, root, predicates, "utrReferenceNumber", criteria.getUtrReferenceNumber(), true);
                addStringFilter(cb, root, predicates, "paymentGatewayResponsePayload", criteria.getPaymentGatewayResponsePayload(), true);
                addStringFilter(cb, root, predicates, "coreBankingResponsePayload", criteria.getCoreBankingResponsePayload(), true);

                addStringFilter(cb, root, predicates, "transactionDateTime", criteria.getTransactionDateTime(), true);
                addStringFilter(cb, root, predicates, "transactionStatus", criteria.getTransactionStatus(), true);
                addBooleanFilter(cb, root, predicates, "tasPaymentFlag", criteria.getTasPaymentFlag());
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());

                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());


                // relationships

                if (criteria.getPaymentTypeId() != null) {
                    Join<PaymentInstruction, ApplicationSetting> join = root.join("paymentType", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getPaymentTypeId());
                }

                if (criteria.getAdhocPaymentId() != null) {
                    Join<PaymentInstruction, ApplicationSetting> join = root.join("adhocPayment", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getAdhocPaymentId());
                }

                if (criteria.getPurposeId() != null) {
                    Join<PaymentInstruction, ApplicationSetting> join = root.join("purpose", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getPurposeId());
                }

                if (criteria.getBeneficiaryCurrencyId() != null) {
                    Join<PaymentInstruction, ApplicationSetting> join = root.join("beneficiaryCurrency", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getBeneficiaryCurrencyId());
                }

            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
