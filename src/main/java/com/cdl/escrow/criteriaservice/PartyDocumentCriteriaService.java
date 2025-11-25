package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.PartyDocumentCriteria;
import com.cdl.escrow.dto.PartyDocumentDTO;
import com.cdl.escrow.entity.PartyDocument;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.PartyDocumentMapper;
import com.cdl.escrow.repository.PartyDocumentRepository;
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
public class PartyDocumentCriteriaService extends BaseSpecificationBuilder<PartyDocument> implements Serializable {

    private final transient PartyDocumentRepository partyDocumentRepository;

    private final transient PartyDocumentMapper partyDocumentMapper;

    public Page<PartyDocumentDTO> findByCriteria(PartyDocumentCriteria criteria, Pageable pageable) {
        Specification<PartyDocument> specification = createSpecification(criteria);
        return partyDocumentRepository.findAll(specification, pageable).map(partyDocumentMapper::toDto);
    }

    private Specification<PartyDocument> createSpecification(PartyDocumentCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "uuid", criteria.getUuid(), true);
                addStringFilter(cb, root, predicates, "documentName", criteria.getDocumentName(), true);
                addStringFilter(cb, root, predicates, "documentDescription", criteria.getDocumentDescription(), true);
                addStringFilter(cb, root, predicates, "documentTypeCode", criteria.getDocumentTypeCode(),true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
