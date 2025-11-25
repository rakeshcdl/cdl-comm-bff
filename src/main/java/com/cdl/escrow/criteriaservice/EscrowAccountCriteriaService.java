package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.EscrowAccountCriteria;
import com.cdl.escrow.dto.EscrowAccountDTO;
import com.cdl.escrow.entity.EscrowAccount;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.EscrowAccountMapper;
import com.cdl.escrow.repository.EscrowAccountRepository;
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
public class EscrowAccountCriteriaService extends BaseSpecificationBuilder<EscrowAccount> implements Serializable {

    private final transient EscrowAccountRepository escrowAccountRepository;

    private final transient EscrowAccountMapper escrowAccountMapper;

    public Page<EscrowAccountDTO> findByCriteria(EscrowAccountCriteria criteria, Pageable pageable) {
        Specification<EscrowAccount> specification = createSpecification(criteria);
        return escrowAccountRepository.findAll(specification, pageable).map(escrowAccountMapper::toDto);
    }

    private Specification<EscrowAccount> createSpecification(EscrowAccountCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "UUID", criteria.getUUID(), true);
                addStringFilter(cb, root, predicates, "accountNumber", criteria.getAccountNumber(), true);
                addStringFilter(cb, root, predicates, "productCode", criteria.getProductCode(), true);
                addStringFilter(cb, root, predicates, "accountDisplayName", criteria.getAccountDisplayName(), true);
                addStringFilter(cb, root, predicates, "ibanNumber", criteria.getIbanNumber(), true);
                addStringFilter(cb, root, predicates, "officialAccountTitle", criteria.getOfficialAccountTitle(), true);
                addStringFilter(cb, root, predicates, "virtualAccountNumber", criteria.getVirtualAccountNumber(), true);
                addStringFilter(cb, root, predicates, "accountTypeCode", criteria.getAccountTypeCode(), true);
                addStringFilter(cb, root, predicates, "assignmentStatus", criteria.getAssignmentStatus(), true);

                addStringFilter(cb, root, predicates, "assignedToReference", criteria.getAssignedToReference(), true);
                addZonedDateTimeFilter(cb, root, predicates, "accountOpenDateTime", criteria.getAccountOpenDateTime());
                addStringFilter(cb, root, predicates, "referenceField1", criteria.getReferenceField1(), true);
                addStringFilter(cb, root, predicates, "referenceField2", criteria.getReferenceField2(), true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
