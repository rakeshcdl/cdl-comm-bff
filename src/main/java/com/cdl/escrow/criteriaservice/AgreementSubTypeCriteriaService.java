package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.AgreementSubTypeCriteria;
import com.cdl.escrow.dto.AgreementSubTypeDTO;
import com.cdl.escrow.entity.AgreementSignatory;
import com.cdl.escrow.entity.AgreementSubType;
import com.cdl.escrow.entity.AgreementType;
import com.cdl.escrow.entity.AuthorizedSignatory;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.AgreementSubTypeMapper;
import com.cdl.escrow.repository.AgreementSubTypeRepository;
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
public class AgreementSubTypeCriteriaService extends BaseSpecificationBuilder<AgreementSubType> implements Serializable {

    private final transient AgreementSubTypeRepository agreementSubTypeRepository;

    private final transient AgreementSubTypeMapper agreementSubTypeMapper;

    public Page<AgreementSubTypeDTO> findByCriteria(AgreementSubTypeCriteria criteria, Pageable pageable) {
        Specification<AgreementSubType> specification = createSpecification(criteria);
        return agreementSubTypeRepository.findAll(specification, pageable).map(agreementSubTypeMapper::toDto);
    }

    private Specification<AgreementSubType> createSpecification(AgreementSubTypeCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "UUID", criteria.getUUID(), true);
                addStringFilter(cb, root, predicates, "subTypeName", criteria.getSubTypeName(), true);
                addStringFilter(cb, root, predicates, "subTypeDescription", criteria.getSubTypeDescription(), true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());

                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());

                // relationships

                if (criteria.getAgreementTypeId() != null) {
                    Join<AgreementSubType, AgreementType> join = root.join("agreementType", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getAgreementTypeId());
                }


            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
