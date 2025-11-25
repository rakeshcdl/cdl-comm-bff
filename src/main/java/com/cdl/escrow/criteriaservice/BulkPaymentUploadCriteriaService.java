package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.BulkPaymentUploadCriteria;
import com.cdl.escrow.dto.BulkPaymentUploadDTO;
import com.cdl.escrow.entity.*;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.BulkPaymentUploadMapper;
import com.cdl.escrow.repository.BulkPaymentUploadRepository;
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
public class BulkPaymentUploadCriteriaService extends BaseSpecificationBuilder<BulkPaymentUpload> implements Serializable {

    private final transient BulkPaymentUploadRepository bulkPaymentUploadRepository;

    private final transient BulkPaymentUploadMapper bulkPaymentUploadMapper;

    public Page<BulkPaymentUploadDTO> findByCriteria(BulkPaymentUploadCriteria criteria, Pageable pageable) {
        Specification<BulkPaymentUpload> specification = createSpecification(criteria);
        return bulkPaymentUploadRepository.findAll(specification, pageable).map(bulkPaymentUploadMapper::toDto);
    }

    private Specification<BulkPaymentUpload> createSpecification(BulkPaymentUploadCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "UUID", criteria.getUUID(), true);
                addDoubleFilter(cb, root, predicates, "paymentAmount", criteria.getPaymentAmount());
                addStringFilter(cb, root, predicates, "batchRemarks", criteria.getBatchRemarks(), true);
                addStringFilter(cb, root, predicates, "utrReferenceNumber", criteria.getUtrReferenceNumber(), true);
                addStringFilter(cb, root, predicates, "transactionStatus", criteria.getTransactionStatus(), true);
                addStringFilter(cb, root, predicates, "transactionRemarks", criteria.getTransactionRemarks(), true);
                addDoubleFilter(cb, root, predicates, "exchangeRate", criteria.getExchangeRate());
                addDoubleFilter(cb, root, predicates, "convertedPaymentAmount", criteria.getConvertedPaymentAmount());
                addStringFilter(cb, root, predicates, "batchReferenceId", criteria.getBatchReferenceId(), true);
                addStringFilter(cb, root, predicates, "payerAddressLine1", criteria.getPayerAddressLine1(), true);
                addStringFilter(cb, root, predicates, "payerAddressLine2", criteria.getPayerAddressLine2(), true);
                addStringFilter(cb, root, predicates, "payerAddressLine3", criteria.getPayerAddressLine3(), true);
                addStringFilter(cb, root, predicates, "transactionDateTime", criteria.getTransactionDateTime(), true);
                addStringFilter(cb, root, predicates, "paymentResponsePayload", criteria.getPaymentResponsePayload(), true);
                addStringFilter(cb, root, predicates, "sourceEntityType", criteria.getSourceEntityType(), true);
                addStringFilter(cb, root, predicates, "documentName", criteria.getDocumentName(), true);
                addStringFilter(cb, root, predicates, "recordValidationStatus", criteria.getRecordValidationStatus(), true);
                addStringFilter(cb, root, predicates, "validationErrorMessage", criteria.getValidationErrorMessage(), true);
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());

                // relationships

                if (criteria.getPartyId() != null) {
                    Join<BulkPaymentUpload, Party> join = root.join("party", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getPartyId());
                }

                if (criteria.getEscrowAccountId() != null) {
                    Join<BulkPaymentUpload, EscrowAgreement> join = root.join("escrowAgreement", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getEscrowAccountId());
                }

                if (criteria.getEscrowAccountId() != null) {
                    Join<BulkPaymentUpload, EscrowAccount> join = root.join("escrowAccount", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getEscrowAccountId());
                }

                if (criteria.getTransactionTypeId() != null) {
                    Join<BulkPaymentUpload, ApplicationSetting> join = root.join("transactionType", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getTransactionTypeId());
                }

                if (criteria.getCurrencyId() != null) {
                    Join<BulkPaymentUpload, ApplicationSetting> join = root.join("currency", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getCurrencyId());
                }

            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
