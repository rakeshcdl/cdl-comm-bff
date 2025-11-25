package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.GeneralLedgerAccountCriteria;
import com.cdl.escrow.dto.GeneralLedgerAccountDTO;
import com.cdl.escrow.entity.GeneralLedgerAccount;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.GeneralLedgerAccountMapper;
import com.cdl.escrow.repository.GeneralLedgerAccountRepository;
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
public class GeneralLedgerAccountCriteriaService extends BaseSpecificationBuilder<GeneralLedgerAccount> implements Serializable {

    private final transient GeneralLedgerAccountRepository generalLedgerAccountRepository;

    private final transient GeneralLedgerAccountMapper generalLedgerAccountMapper;

    public Page<GeneralLedgerAccountDTO> findByCriteria(GeneralLedgerAccountCriteria criteria, Pageable pageable) {
        Specification<GeneralLedgerAccount> specification = createSpecification(criteria);
        return generalLedgerAccountRepository.findAll(specification, pageable).map(generalLedgerAccountMapper::toDto);
    }

    private Specification<GeneralLedgerAccount> createSpecification(GeneralLedgerAccountCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "uuid", criteria.getUuid(), true);
                addStringFilter(cb, root, predicates, "ledgerAccountNumber", criteria.getLedgerAccountNumber(), true);
                addStringFilter(cb, root, predicates, "branchIdentifierCode", criteria.getBranchIdentifierCode(), true);
                addStringFilter(cb, root, predicates, "ledgerAccountDescription", criteria.getLedgerAccountDescription(),true);
                addStringFilter(cb, root, predicates, "ledgerAccountTypeCode", criteria.getLedgerAccountTypeCode(),true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
