package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.AgreementSegmentCriteria;
import com.cdl.escrow.dto.AgreementSegmentDTO;
import com.cdl.escrow.entity.AgreementSegment;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.AgreementSegmentMapper;
import com.cdl.escrow.repository.AgreementSegmentRepository;
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
public class AgreementSegmentCriteriaService extends BaseSpecificationBuilder<AgreementSegment> implements Serializable {

    private final transient AgreementSegmentRepository agreementSegmentRepository;

    private final transient AgreementSegmentMapper agreementSegmentMapper;

    public Page<AgreementSegmentDTO> findByCriteria(AgreementSegmentCriteria criteria, Pageable pageable) {
        Specification<AgreementSegment> specification = createSpecification(criteria);
        return agreementSegmentRepository.findAll(specification, pageable).map(agreementSegmentMapper::toDto);
    }

    private Specification<AgreementSegment> createSpecification(AgreementSegmentCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "uuid", criteria.getUuid(), true);
                addStringFilter(cb, root, predicates, "segmentName", criteria.getSegmentName(), true);
                addStringFilter(cb, root, predicates, "segmentDescription", criteria.getSegmentDescription(), true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());

            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
