package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.BusinessSegmentCriteria;
import com.cdl.escrow.dto.BusinessSegmentDTO;
import com.cdl.escrow.entity.BusinessSegment;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.BusinessSegmentMapper;
import com.cdl.escrow.repository.BusinessSegmentRepository;
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
public class BusinessSegmentCriteriaService extends BaseSpecificationBuilder<BusinessSegment> implements Serializable {

    private final transient BusinessSegmentRepository businessSegmentRepository;

    private final transient BusinessSegmentMapper businessSegmentMapper;

    public Page<BusinessSegmentDTO> findByCriteria(BusinessSegmentCriteria criteria, Pageable pageable) {
        Specification<BusinessSegment> specification = createSpecification(criteria);
        return businessSegmentRepository.findAll(specification, pageable).map(businessSegmentMapper::toDto);
    }

    private Specification<BusinessSegment> createSpecification(BusinessSegmentCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "uuid", criteria.getUuid(), true);
                addStringFilter(cb, root, predicates, "segmentName", criteria.getSegmentName(), true);
                addStringFilter(cb, root, predicates, "segmentDescription", criteria.getSegmentDescription(), true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());


            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
