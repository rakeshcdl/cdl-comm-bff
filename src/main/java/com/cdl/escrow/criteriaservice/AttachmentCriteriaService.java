package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.AttachmentCriteria;
import com.cdl.escrow.dto.AttachmentDTO;
import com.cdl.escrow.entity.*;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.AttachmentMapper;
import com.cdl.escrow.repository.AttachmentRepository;
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
public class AttachmentCriteriaService extends BaseSpecificationBuilder<Attachment> implements Serializable {

    private final transient AttachmentRepository attachmentRepository;

    private final transient AttachmentMapper attachmentMapper;

    public Page<AttachmentDTO> findByCriteria(AttachmentCriteria criteria, Pageable pageable) {
        Specification<Attachment> specification = createSpecification(criteria);
        return attachmentRepository.findAll(specification, pageable).map(attachmentMapper::toDto);
    }

    private Specification<Attachment> createSpecification(AttachmentCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "name", criteria.getName(), true);
                addStringFilter(cb, root, predicates, "location", criteria.getLocation(), true);
                addStringFilter(cb, root, predicates, "module", criteria.getModule(), true);
                addLongFilter(cb, root, predicates, "recordId", criteria.getRecordId());
                addStringFilter(cb, root, predicates, "storageType", criteria.getStorageType(),true);
                addZonedDateTimeFilter(cb, root, predicates, "uploadDate", criteria.getUploadDate());
                addStringFilter(cb, root, predicates, "documentSize", criteria.getDocumentSize(), true);
                addZonedDateTimeFilter(cb, root, predicates, "validityDate", criteria.getValidityDate());

                // relationships

                if (criteria.getPartyDocumentId() != null) {
                    Join<Attachment, PartyDocument> join = root.join("partyDocument", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getPartyDocumentId());
                }

                if (criteria.getDocumentTypeId() != null) {
                    Join<Attachment, ApplicationSetting> join = root.join("documentType", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getDocumentTypeId());
                }

            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
