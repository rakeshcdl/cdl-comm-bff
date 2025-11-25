package com.cdl.escrow.criteriaservice;


import com.cdl.escrow.criteria.CurrencyCriteria;
import com.cdl.escrow.dto.CurrencyDTO;
import com.cdl.escrow.entity.Currency;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.CurrencyMapper;
import com.cdl.escrow.repository.CurrencyRepository;
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
public class CurrencyCriteriaService extends BaseSpecificationBuilder<Currency> implements Serializable {

    private final transient CurrencyRepository currencyRepository;

    private final transient CurrencyMapper currencyMapper;

    public Page<CurrencyDTO> findByCriteria(CurrencyCriteria criteria, Pageable pageable) {
        Specification<Currency> specification = createSpecification(criteria);
        return currencyRepository.findAll(specification, pageable).map(currencyMapper::toDto);
    }

    private Specification<Currency> createSpecification(CurrencyCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "uuid", criteria.getUuid(), true);
                addStringFilter(cb, root, predicates, "description", criteria.getDescription(), true);
                addBooleanFilter(cb, root, predicates, "isEnabled", criteria.getEnabled());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
