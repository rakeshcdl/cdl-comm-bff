package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.AgreementParametersCriteria;
import com.cdl.escrow.dto.AgreementParametersDTO;
import com.cdl.escrow.entity.AgreementParameters;
import com.cdl.escrow.entity.ApplicationSetting;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.AgreementParametersMapper;
import com.cdl.escrow.repository.AgreementParametersRepository;
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
public class AgreementParametersCriteriaService extends BaseSpecificationBuilder<AgreementParameters> implements Serializable {

    private final transient AgreementParametersRepository agreementParametersRepository;

    private final transient AgreementParametersMapper agreementParametersMapper;

    public Page<AgreementParametersDTO> findByCriteria(AgreementParametersCriteria criteria, Pageable pageable) {
        Specification<AgreementParameters> specification = createSpecification(criteria);
        return agreementParametersRepository.findAll(specification, pageable).map(agreementParametersMapper::toDto);
    }

    private Specification<AgreementParameters> createSpecification(AgreementParametersCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "UUID", criteria.getUUID(), true);
                addZonedDateTimeFilter(cb, root, predicates, "agreementEffectiveDate", criteria.getAgreementEffectiveDate());
                addZonedDateTimeFilter(cb, root, predicates, "agreementExpiryDate", criteria.getAgreementExpiryDate());
                addStringFilter(cb, root, predicates, "agreementRemarks", criteria.getAgreementRemarks(),true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());

                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());

                // relationships

                if (criteria.getPermittedInvestmentAllowedId() != null) {
                    Join<AgreementParameters, ApplicationSetting> join = root.join("permittedInvestmentAllowed", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getPermittedInvestmentAllowedId());
                }

                if (criteria.getAmendmentAllowedId() != null) {
                    Join<AgreementParameters, ApplicationSetting> join = root.join("amendmentAllowed", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getAmendmentAllowedId());
                }

                if (criteria.getDealClosureBasisId() != null) {
                    Join<AgreementParameters, ApplicationSetting> join = root.join("dealClosureBasis", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getDealClosureBasisId());
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
