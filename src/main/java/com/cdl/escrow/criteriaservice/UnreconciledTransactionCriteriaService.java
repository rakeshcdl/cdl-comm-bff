package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.UnreconciledTransactionCriteria;
import com.cdl.escrow.dto.UnreconciledTransactionDTO;
import com.cdl.escrow.entity.UnreconciledTransaction;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.UnreconciledTransactionMapper;
import com.cdl.escrow.repository.UnreconciledTransactionRepository;
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
public class UnreconciledTransactionCriteriaService extends BaseSpecificationBuilder<UnreconciledTransaction> implements Serializable {

    private final transient UnreconciledTransactionRepository unreconciledTransactionRepository;

    private final transient UnreconciledTransactionMapper unreconciledTransactionMapper;

    public Page<UnreconciledTransactionDTO> findByCriteria(UnreconciledTransactionCriteria criteria, Pageable pageable) {
        Specification<UnreconciledTransaction> specification = createSpecification(criteria);
        return unreconciledTransactionRepository.findAll(specification, pageable).map(unreconciledTransactionMapper::toDto);
    }

    private Specification<UnreconciledTransaction> createSpecification(UnreconciledTransactionCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "unReconTransactionId", criteria.getUnReconTransactionId(), true);
                addStringFilter(cb, root, predicates, "transactionReferenceNumber", criteria.getTransactionReferenceNumber(), true);

                addBigDecimalFilter(cb, root, predicates, "transactionAmount", criteria.getTransactionAmount());
                addBigDecimalFilter(cb, root, predicates, "totalTransactionAmount", criteria.getTotalTransactionAmount());

                addZonedDateTimeFilter(cb, root, predicates, "transactionDateTime", criteria.getTransactionDateTime());
                addStringFilter(cb, root, predicates, "transactionNarration", criteria.getTransactionNarration(), true);
                addStringFilter(cb, root, predicates, "transactionDescription", criteria.getTransactionDescription(), true);
                addBooleanFilter(cb, root, predicates, "discardFlag", criteria.getDiscardFlag());
                addBooleanFilter(cb, root, predicates, "allocatedFlag", criteria.getAllocatedFlag());

               // addStringFilter(cb, root, predicates, "processingRemarks", criteria.getProcessingRemarks(), true);

                addStringFilter(cb, root, predicates, "transactionParticular1", criteria.getTransactionParticular1(), true);
                addStringFilter(cb, root, predicates, "transactionParticular2", criteria.getTransactionParticular2(), true);
                addStringFilter(cb, root, predicates, "transactionParticularRemark1", criteria.getTransactionParticularRemark1(), true);
                addStringFilter(cb, root, predicates, "transactionParticularRemark2", criteria.getTransactionParticularRemark2(), true);
                addStringFilter(cb, root, predicates, "chequeReferenceNumber", criteria.getChequeReferenceNumber(), true);
                addBooleanFilter(cb, root, predicates, "tasUpdateRequestedFlag", criteria.getTasUpdateRequestedFlag());
                addBooleanFilter(cb, root, predicates, "tasUpdateAppliedFlag", criteria.getTasUpdateAppliedFlag());
                addZonedDateTimeFilter(cb, root, predicates, "valueDateTime", criteria.getValueDateTime());
                addZonedDateTimeFilter(cb, root, predicates, "postedDateTime", criteria.getPostedDateTime());
                addZonedDateTimeFilter(cb, root, predicates, "processingDateTime", criteria.getProcessingDateTime());


                addStringFilter(cb, root, predicates, "branchIdentifierCode", criteria.getBranchIdentifierCode(), true);
                addStringFilter(cb, root, predicates, "postedBranchIdentifierCode", criteria.getPostedBranchIdentifierCode(), true);
                addStringFilter(cb, root, predicates, "currencyCode", criteria.getCurrencyCode(), true);

                addStringFilter(cb, root, predicates, "customField1", criteria.getCustomField1(), true);
                addStringFilter(cb, root, predicates, "customField2", criteria.getCustomField2(), true);
                addStringFilter(cb, root, predicates, "customField3", criteria.getCustomField3(), true);
                addStringFilter(cb, root, predicates, "customField4", criteria.getCustomField4(), true);
                addStringFilter(cb, root, predicates, "customField5", criteria.getCustomField5(), true);
                addStringFilter(cb, root, predicates, "primaryUnitHolderFullName", criteria.getPrimaryUnitHolderFullName(), true);
                addBooleanFilter(cb, root, predicates, "unallocatedCategoryFlag", criteria.getUnallocatedCategoryFlag());
                addStringFilter(cb, root, predicates, "tasPaymentStatusCode", criteria.getTasPaymentStatusCode(), true);

                addZonedDateTimeFilter(cb, root, predicates, "discardedDateTime", criteria.getDiscardedDateTime());
                addBooleanFilter(cb, root, predicates, "creditedToEscrowFlag", criteria.getCreditedToEscrowFlag());
                addStringFilter(cb, root, predicates, "paymentReferenceNumber", criteria.getPaymentReferenceNumber(), true);
                addStringFilter(cb, root, predicates, "subBucketIdentifier", criteria.getSubBucketIdentifier(), true);

                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
