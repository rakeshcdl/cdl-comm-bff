package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.CountryCriteria;
import com.cdl.escrow.dto.CountryDTO;
import com.cdl.escrow.entity.Country;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.CountryMapper;
import com.cdl.escrow.repository.CountryRepository;
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
public class CountryCriteriaService extends BaseSpecificationBuilder<Country> implements Serializable {

    private final transient CountryRepository countryRepository;

    private final transient CountryMapper countryMapper;

    public Page<CountryDTO> findByCriteria(CountryCriteria criteria, Pageable pageable) {
        Specification<Country> specification = createSpecification(criteria);
        return countryRepository.findAll(specification, pageable).map(countryMapper::toDto);
    }

    private Specification<Country> createSpecification(CountryCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "uuid", criteria.getUuid(), true);
                addStringFilter(cb, root, predicates, "description", criteria.getDescription(), true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
