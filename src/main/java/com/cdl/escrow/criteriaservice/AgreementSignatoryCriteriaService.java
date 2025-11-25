package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.AgreementSignatoryCriteria;
import com.cdl.escrow.dto.AgreementSignatoryDTO;
import com.cdl.escrow.entity.AgreementSignatory;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.AgreementSignatoryMapper;
import com.cdl.escrow.repository.AgreementSignatoryRepository;
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
public class AgreementSignatoryCriteriaService extends BaseSpecificationBuilder<AgreementSignatory> implements Serializable {

    private final transient AgreementSignatoryRepository agreementSignatoryRepository;

    private final transient AgreementSignatoryMapper agreementSignatoryMapper;

    public Page<AgreementSignatoryDTO> findByCriteria(AgreementSignatoryCriteria criteria, Pageable pageable) {
        Specification<AgreementSignatory> specification = createSpecification(criteria);
        return agreementSignatoryRepository.findAll(specification, pageable).map(agreementSignatoryMapper::toDto);
    }

    private Specification<AgreementSignatory> createSpecification(AgreementSignatoryCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "partyReferenceNumber", criteria.getPartyReferenceNumber(), true);
                addStringFilter(cb, root, predicates, "partyCustomerReferenceNumber", criteria.getPartyCustomerReferenceNumber(), true);
                addStringFilter(cb, root, predicates, "partyFullName", criteria.getPartyFullName(), true);
                addStringFilter(cb, root, predicates, "addressLine1", criteria.getAddressLine1(),true);
                addStringFilter(cb, root, predicates, "addressLine2", criteria.getAddressLine2(), true);

                addStringFilter(cb, root, predicates, "addressLine3", criteria.getAddressLine3(), true);
                addStringFilter(cb, root, predicates, "signatoryRole", criteria.getSignatoryRole(), true);
                addStringFilter(cb, root, predicates, "notificationContactName", criteria.getNotificationContactName(), true);
                addStringFilter(cb, root, predicates, "notificationAddressLine1", criteria.getNotificationAddressLine1(), true);
                addStringFilter(cb, root, predicates, "notificationAddressLine2", criteria.getNotificationAddressLine2(), true);
                addStringFilter(cb, root, predicates, "notificationAddressLine3", criteria.getNotificationAddressLine3(), true);
                addStringFilter(cb, root, predicates, "notificationEmailAddress", criteria.getNotificationEmailAddress(), true);
                addStringFilter(cb, root, predicates, "associationType", criteria.getAssociationType(), true);

                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
