package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.AgreementBudgetPlanCriteria;
import com.cdl.escrow.criteria.AppLanguageCodeCriteria;
import com.cdl.escrow.dto.AgreementBudgetPlanDTO;
import com.cdl.escrow.dto.AppLanguageCodeDTO;
import com.cdl.escrow.entity.AccountType;
import com.cdl.escrow.entity.AgreementBudgetPlan;
import com.cdl.escrow.entity.AppLanguageCode;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.AccountTypeMapper;
import com.cdl.escrow.mapper.AgreementBudgetPlanMapper;
import com.cdl.escrow.repository.AccountTypeRepository;
import com.cdl.escrow.repository.AgreementBudgetPlanRepository;
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
public class AgreementBudgetPlanCriteriaService extends BaseSpecificationBuilder<AgreementBudgetPlan> implements Serializable {

    private final transient AgreementBudgetPlanRepository agreementBudgetPlanRepository;

    private final transient AgreementBudgetPlanMapper agreementBudgetPlanMapper;

    public Page<AgreementBudgetPlanDTO> findByCriteria(AgreementBudgetPlanCriteria criteria, Pageable pageable) {
        Specification<AgreementBudgetPlan> specification = createSpecification(criteria);
        return agreementBudgetPlanRepository.findAll(specification, pageable).map(agreementBudgetPlanMapper::toDto);
    }

    private Specification<AgreementBudgetPlan> createSpecification(AgreementBudgetPlanCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "expenseMetadataJson", criteria.getExpenseMetadataJson(), true);
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
