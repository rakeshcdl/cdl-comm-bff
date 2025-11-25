package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.InvestmentCriteria;
import com.cdl.escrow.dto.InvestmentDTO;
import com.cdl.escrow.entity.Investment;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.InvestmentMapper;
import com.cdl.escrow.repository.InvestmentRepository;
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
public class InvestmentCriteriaService extends BaseSpecificationBuilder<Investment> implements Serializable {

    private final transient InvestmentRepository investmentRepository;

    private final transient InvestmentMapper investmentMapper;

    public Page<InvestmentDTO> findByCriteria(InvestmentCriteria criteria, Pageable pageable) {
        Specification<Investment> specification = createSpecification(criteria);
        return investmentRepository.findAll(specification, pageable).map(investmentMapper::toDto);
    }

    private Specification<Investment> createSpecification(InvestmentCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "UUID", criteria.getUUID(), true);
                addStringFilter(cb, root, predicates, "investmentName", criteria.getInvestmentName(), true);
                addStringFilter(cb, root, predicates, "investmentDescription", criteria.getInvestmentDescription(), true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
