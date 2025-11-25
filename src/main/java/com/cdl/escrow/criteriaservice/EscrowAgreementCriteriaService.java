package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.EscrowAgreementCriteria;
import com.cdl.escrow.dto.EscrowAgreementDTO;
import com.cdl.escrow.entity.EscrowAgreement;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.EscrowAgreementMapper;
import com.cdl.escrow.repository.EscrowAgreementRepository;
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
public class EscrowAgreementCriteriaService extends BaseSpecificationBuilder<EscrowAgreement> implements Serializable {

    private final transient EscrowAgreementRepository escrowAgreementRepository;

    private final transient EscrowAgreementMapper escrowAgreementMapper;

    public Page<EscrowAgreementDTO> findByCriteria(EscrowAgreementCriteria criteria, Pageable pageable) {
        Specification<EscrowAgreement> specification = createSpecification(criteria);
        return escrowAgreementRepository.findAll(specification, pageable).map(escrowAgreementMapper::toDto);
    }

    private Specification<EscrowAgreement> createSpecification(EscrowAgreementCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "UUID", criteria.getUUID(), true);
                addStringFilter(cb, root, predicates, "primaryEscrowCifNumber", criteria.getPrimaryEscrowCifNumber(), true);
                addStringFilter(cb, root, predicates, "productManagerName", criteria.getProductManagerName(), true);
                addStringFilter(cb, root, predicates, "relationshipManagerName", criteria.getRelationshipManagerName(), true);
                addStringFilter(cb, root, predicates, "operatingLocationCode", criteria.getOperatingLocationCode(), true);
                addStringFilter(cb, root, predicates, "customField1", criteria.getCustomField1(), true);
                addStringFilter(cb, root, predicates, "customField2", criteria.getCustomField2(), true);
                addStringFilter(cb, root, predicates, "customField3", criteria.getCustomField3(), true);
                addStringFilter(cb, root, predicates, "customField4", criteria.getCustomField4(), true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());

            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
