package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.AccountTypeCriteria;
import com.cdl.escrow.criteria.AppLanguageCodeCriteria;
import com.cdl.escrow.dto.AccountTypeDTO;
import com.cdl.escrow.dto.AppLanguageCodeDTO;
import com.cdl.escrow.entity.AccountType;
import com.cdl.escrow.entity.AccountTypeCategory;
import com.cdl.escrow.entity.AppLanguageCode;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.AccountTypeCategoryMapper;
import com.cdl.escrow.mapper.AccountTypeMapper;
import com.cdl.escrow.repository.AccountTypeCategoryRepository;
import com.cdl.escrow.repository.AccountTypeRepository;
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
public class AccountTypeCriteriaService extends BaseSpecificationBuilder<AccountType> implements Serializable {

    private final transient AccountTypeRepository accountTypeRepository;

    private final transient AccountTypeMapper accountTypeMapper;

    public Page<AccountTypeDTO> findByCriteria(AccountTypeCriteria criteria, Pageable pageable) {
        Specification<AccountType> specification = createSpecification(criteria);
        return accountTypeRepository.findAll(specification, pageable).map(accountTypeMapper::toDto);
    }

    private Specification<AccountType> createSpecification(AccountTypeCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "labelCode", criteria.getLabelCode(), true);
                addStringFilter(cb, root, predicates, "typeCode", criteria.getTypeCode(), true);
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
