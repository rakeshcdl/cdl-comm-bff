package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.ReconciledTransactionCriteria;
import com.cdl.escrow.dto.ReconciledTransactionDTO;
import com.cdl.escrow.entity.ReconciledTransaction;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.ReconciledTransactionMapper;
import com.cdl.escrow.repository.ReconciledTransactionRepository;
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
public class ReconciledTransactionCriteriaService extends BaseSpecificationBuilder<ReconciledTransaction> implements Serializable {

    private final transient ReconciledTransactionRepository reconciledTransactionRepository;

    private final transient ReconciledTransactionMapper reconciledTransactionMapper;

    public Page<ReconciledTransactionDTO> findByCriteria(ReconciledTransactionCriteria criteria, Pageable pageable) {
        Specification<ReconciledTransaction> specification = createSpecification(criteria);
        return reconciledTransactionRepository.findAll(specification, pageable).map(reconciledTransactionMapper::toDto);
    }

    private Specification<ReconciledTransaction> createSpecification(ReconciledTransactionCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "reconTransactionId", criteria.getReconTransactionId(), true);
                addBigDecimalFilter(cb, root, predicates, "transactionAmount", criteria.getTransactionAmount());
                addBigDecimalFilter(cb, root, predicates, "totalTransactionAmount", criteria.getTotalTransactionAmount());

                addZonedDateTimeFilter(cb, root, predicates, "transactionDateTime", criteria.getTransactionDateTime());
                addStringFilter(cb, root, predicates, "transactionNarration", criteria.getTransactionNarration(), true);
                addStringFilter(cb, root, predicates, "transactionDescription", criteria.getTransactionDescription(), true);
                addStringFilter(cb, root, predicates, "processingRemarks", criteria.getProcessingRemarks(), true);
                addBooleanFilter(cb, root, predicates, "allocatedFlag", criteria.getAllocatedFlag());
                addStringFilter(cb, root, predicates, "transactionParticular1", criteria.getTransactionParticular1(), true);
                addStringFilter(cb, root, predicates, "transactionParticular2", criteria.getTransactionParticular2(), true);
                addStringFilter(cb, root, predicates, "transactionParticularRemark1", criteria.getTransactionParticularRemark1(), true);
                addStringFilter(cb, root, predicates, "transactionParticularRemark2", criteria.getTransactionParticularRemark2(), true);
                addStringFilter(cb, root, predicates, "chequeReferenceNumber", criteria.getChequeReferenceNumber(), true);
                addBooleanFilter(cb, root, predicates, "tasUpdateRequestedFlag", criteria.getTasUpdateRequestedFlag());
                addBooleanFilter(cb, root, predicates, "tasUpdateAppliedFlag", criteria.getTasUpdateAppliedFlag());
                addBooleanFilter(cb, root, predicates, "tasUpdateEnabledFlag", criteria.getTasUpdateEnabledFlag());
                addStringFilter(cb, root, predicates, "unitReferenceNumber", criteria.getUnitReferenceNumber(), true);
                addStringFilter(cb, root, predicates, "tasPaymentStatusCode", criteria.getTasPaymentStatusCode(), true);
                addStringFilter(cb, root, predicates, "batchTransactionId", criteria.getBatchTransactionId(), true);

                addBooleanFilter(cb, root, predicates, "rollbackFlag", criteria.getRollbackFlag());
                addStringFilter(cb, root, predicates, "coreBankingResponsePayload", criteria.getCoreBankingResponsePayload(), true);
                addStringFilter(cb, root, predicates, "paymentReferenceNumber", criteria.getPaymentReferenceNumber(), true);
                addStringFilter(cb, root, predicates, "subBucketIdentifier", criteria.getSubBucketIdentifier(), true);
                addBooleanFilter(cb, root, predicates, "tasUpdateEnabledFlag", criteria.getTasUpdateEnabledFlag());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());

            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
