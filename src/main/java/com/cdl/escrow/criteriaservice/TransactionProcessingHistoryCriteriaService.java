package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.TransactionProcessingHistoryCriteria;
import com.cdl.escrow.dto.TransactionProcessingHistoryDTO;
import com.cdl.escrow.entity.TransactionProcessingHistory;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.TransactionProcessingHistoryMapper;
import com.cdl.escrow.repository.TransactionProcessingHistoryRepository;
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
public class TransactionProcessingHistoryCriteriaService extends BaseSpecificationBuilder<TransactionProcessingHistory> implements Serializable {

    private final transient TransactionProcessingHistoryRepository transactionProcessingHistoryRepository;

    private final transient TransactionProcessingHistoryMapper transactionProcessingHistoryMapper;

    public Page<TransactionProcessingHistoryDTO> findByCriteria(TransactionProcessingHistoryCriteria criteria, Pageable pageable) {
        Specification<TransactionProcessingHistory> specification = createSpecification(criteria);
        return transactionProcessingHistoryRepository.findAll(specification, pageable).map(transactionProcessingHistoryMapper::toDto);
    }

    private Specification<TransactionProcessingHistory> createSpecification(TransactionProcessingHistoryCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addIntegerFilter(cb, root, predicates, "retryAttemptCount", criteria.getRetryAttemptCount());
                addLongFilter(cb, root, predicates, "scheduledJobReferenceId", criteria.getScheduledJobReferenceId());
                addZonedDateTimeFilter(cb, root, predicates, "createdDateTime", criteria.getCreatedDateTime());

                addZonedDateTimeFilter(cb, root, predicates, "lastUpdatedDateTime", criteria.getLastUpdatedDateTime());
                addStringFilter(cb, root, predicates, "utrReferenceNumber", criteria.getUtrReferenceNumber(), true);
                addZonedDateTimeFilter(cb, root, predicates, "transactionDateTime", criteria.getTransactionDateTime());
                addBigDecimalFilter(cb, root, predicates, "creditedAmount", criteria.getCreditedAmount());
                addStringFilter(cb, root, predicates, "processingStatusCode", criteria.getProcessingStatusCode(), true);
                addStringFilter(cb, root, predicates, "initiatedBy", criteria.getInitiatedBy(), true);
                addStringFilter(cb, root, predicates, "remarks", criteria.getRemarks(), true);
                addStringFilter(cb, root, predicates, "correlationId", criteria.getCorrelationId(), true);

                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());

            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
