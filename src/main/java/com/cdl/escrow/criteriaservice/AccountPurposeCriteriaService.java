package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.AccountPurposeCriteria;
import com.cdl.escrow.criteria.AppLanguageCodeCriteria;
import com.cdl.escrow.dto.AccountPurposeDTO;
import com.cdl.escrow.dto.AppLanguageCodeDTO;
import com.cdl.escrow.entity.AccountPurpose;
import com.cdl.escrow.entity.AppLanguageCode;
import com.cdl.escrow.entity.ApplicationModule;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.AccountPurposeMapper;
import com.cdl.escrow.mapper.ApplicationModuleMapper;
import com.cdl.escrow.repository.AccountPurposeRepository;
import com.cdl.escrow.repository.ApplicationModuleRepository;
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
public class AccountPurposeCriteriaService extends BaseSpecificationBuilder<AccountPurpose> implements Serializable {

    private final transient AccountPurposeRepository accountPurposeRepository;

    private final transient AccountPurposeMapper accountPurposeMapper;

    public Page<AccountPurposeDTO> findByCriteria(AccountPurposeCriteria criteria, Pageable pageable) {
        Specification<AccountPurpose> specification = createSpecification(criteria);
        return accountPurposeRepository.findAll(specification, pageable).map(accountPurposeMapper::toDto);
    }

    private Specification<AccountPurpose> createSpecification(AccountPurposeCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "UUID", criteria.getUUID(), true);
                addStringFilter(cb, root, predicates, "accountPurposeCode", criteria.getAccountPurposeCode(), true);
                addStringFilter(cb, root, predicates, "accountPurposeName", criteria.getAccountPurposeName(), true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
