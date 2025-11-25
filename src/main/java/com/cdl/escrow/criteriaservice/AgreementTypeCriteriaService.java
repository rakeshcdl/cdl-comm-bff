package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.AgreementTypeCriteria;
import com.cdl.escrow.dto.AgreementTypeDTO;
import com.cdl.escrow.entity.AgreementType;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.AgreementTypeMapper;
import com.cdl.escrow.repository.AgreementTypeRepository;
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
public class AgreementTypeCriteriaService extends BaseSpecificationBuilder<AgreementType> implements Serializable {

    private final transient AgreementTypeRepository agreementTypeRepository;

    private final transient AgreementTypeMapper agreementTypeMapper;

    public Page<AgreementTypeDTO> findByCriteria(AgreementTypeCriteria criteria, Pageable pageable) {
        Specification<AgreementType> specification = createSpecification(criteria);
        return agreementTypeRepository.findAll(specification, pageable).map(agreementTypeMapper::toDto);
    }

    private Specification<AgreementType> createSpecification(AgreementTypeCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "uuid", criteria.getUuid(), true);
                addStringFilter(cb, root, predicates, "agreementTypeName", criteria.getAgreementTypeName(), true);
                addStringFilter(cb, root, predicates, "agreementTypeDescription", criteria.getAgreementTypeDescription(), true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());

                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
