package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.BeneficiaryCriteria;
import com.cdl.escrow.dto.BeneficiaryDTO;
import com.cdl.escrow.entity.ApplicationSetting;
import com.cdl.escrow.entity.Beneficiary;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.BeneficiaryMapper;
import com.cdl.escrow.repository.BeneficiaryRepository;
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
public class BeneficiaryCriteriaService extends BaseSpecificationBuilder<Beneficiary> implements Serializable {

    private final transient BeneficiaryRepository beneficiaryRepository;

    private final transient BeneficiaryMapper beneficiaryMapper;

    public Page<BeneficiaryDTO> findByCriteria(BeneficiaryCriteria criteria, Pageable pageable) {
        Specification<Beneficiary> specification = createSpecification(criteria);
        return beneficiaryRepository.findAll(specification, pageable).map(beneficiaryMapper::toDto);
    }

    private Specification<Beneficiary> createSpecification(BeneficiaryCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "UUID", criteria.getUUID(), true);
                addStringFilter(cb, root, predicates, "beneficiaryFullName", criteria.getBeneficiaryFullName(), true);
                addStringFilter(cb, root, predicates, "beneficiaryAddressLine1", criteria.getBeneficiaryAddressLine1(), true);

                addStringFilter(cb, root, predicates, "telephoneNumber", criteria.getTelephoneNumber(), true);
                addStringFilter(cb, root, predicates, "mobileNumber", criteria.getMobileNumber(), true);
                addStringFilter(cb, root, predicates, "beneficiaryAccountNumber", criteria.getBeneficiaryAccountNumber(), true);
                addStringFilter(cb, root, predicates, "bankIfscCode", criteria.getBankIfscCode(), true);
                addStringFilter(cb, root, predicates, "beneficiaryBankName", criteria.getBeneficiaryBankName(), true);
                addStringFilter(cb, root, predicates, "bankRoutingCode", criteria.getBankRoutingCode(), true);

                addStringFilter(cb, root, predicates, "additionalRemarks", criteria.getAdditionalRemarks(), true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());

                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());

                // relationships

                if (criteria.getAccountTypeId() != null) {
                    Join<Beneficiary, ApplicationSetting> join = root.join("accountType", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getAccountTypeId());
                }

                if (criteria.getTransferTypeId() != null) {
                    Join<Beneficiary, ApplicationSetting> join = root.join("transferType", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getTransferTypeId());
                }

                if (criteria.getRoleId() != null) {
                    Join<Beneficiary, ApplicationSetting> join = root.join("role", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getRoleId());
                }


            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
