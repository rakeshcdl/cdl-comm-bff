package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.AccountTypeCategoryCriteria;
import com.cdl.escrow.dto.AccountTypeCategoryDTO;
import com.cdl.escrow.entity.*;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.AccountTypeCategoryMapper;
import com.cdl.escrow.repository.AccountTypeCategoryRepository;
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
public class AccountTypeCategoryCriteriaService extends BaseSpecificationBuilder<AccountTypeCategory> implements Serializable {

    private final transient AccountTypeCategoryRepository accountTypeCategoryRepository;

    private final transient AccountTypeCategoryMapper accountTypeCategoryMapper;

    public Page<AccountTypeCategoryDTO> findByCriteria(AccountTypeCategoryCriteria criteria, Pageable pageable) {
        Specification<AccountTypeCategory> specification = createSpecification(criteria);
        return accountTypeCategoryRepository.findAll(specification, pageable).map(accountTypeCategoryMapper::toDto);
    }

    private Specification<AccountTypeCategory> createSpecification(AccountTypeCategoryCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "labelCode", criteria.getLabelCode(), true);
                addStringFilter(cb, root, predicates, "typeCode", criteria.getTypeCode(), true);
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());

                // relationships

                if (criteria.getAccountTypeId() != null) {
                    Join<AccountTypeCategory, AccountType> join = root.join("accountType", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getAccountTypeId());
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
