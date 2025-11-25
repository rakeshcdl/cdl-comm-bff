package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.ProductProgramCriteria;
import com.cdl.escrow.dto.ProductProgramDTO;
import com.cdl.escrow.entity.ProductProgram;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.ProductProgramMapper;
import com.cdl.escrow.repository.ProductProgramRepository;
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
public class ProductProgramCriteriaService extends BaseSpecificationBuilder<ProductProgram> implements Serializable {

    private final transient ProductProgramRepository productProgramRepository;

    private final transient ProductProgramMapper productProgramMapper;

    public Page<ProductProgramDTO> findByCriteria(ProductProgramCriteria criteria, Pageable pageable) {
        Specification<ProductProgram> specification = createSpecification(criteria);
        return productProgramRepository.findAll(specification, pageable).map(productProgramMapper::toDto);
    }

    private Specification<ProductProgram> createSpecification(ProductProgramCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "UUID", criteria.getUUID(), true);
                addStringFilter(cb, root, predicates, "programName", criteria.getProgramName(), true);
                addStringFilter(cb, root, predicates, "programDescription", criteria.getProgramDescription(), true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
