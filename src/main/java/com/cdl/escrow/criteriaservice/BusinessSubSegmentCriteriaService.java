package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.BusinessSubSegmentCriteria;
import com.cdl.escrow.dto.BusinessSubSegmentDTO;
import com.cdl.escrow.entity.BusinessSubSegment;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.BusinessSubSegmentMapper;
import com.cdl.escrow.repository.BusinessSubSegmentRepository;
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
public class BusinessSubSegmentCriteriaService extends BaseSpecificationBuilder<BusinessSubSegment> implements Serializable {

    private final transient BusinessSubSegmentRepository businessSubSegmentRepository;

    private final transient BusinessSubSegmentMapper businessSubSegmentMapper;

    public Page<BusinessSubSegmentDTO> findByCriteria(BusinessSubSegmentCriteria criteria, Pageable pageable) {
        Specification<BusinessSubSegment> specification = createSpecification(criteria);
        return businessSubSegmentRepository.findAll(specification, pageable).map(businessSubSegmentMapper::toDto);
    }

    private Specification<BusinessSubSegment> createSpecification(BusinessSubSegmentCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "UUID", criteria.getUUID(), true);
                addStringFilter(cb, root, predicates, "subSegmentName", criteria.getSubSegmentName(), true);
                addStringFilter(cb, root, predicates, "subSegmentDescription", criteria.getSubSegmentDescription(), true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
