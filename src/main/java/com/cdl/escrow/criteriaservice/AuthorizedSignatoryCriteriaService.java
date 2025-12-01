package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.AuthorizedSignatoryCriteria;
import com.cdl.escrow.dto.AuthorizedSignatoryDTO;
import com.cdl.escrow.entity.ApplicationSetting;
import com.cdl.escrow.entity.Attachment;
import com.cdl.escrow.entity.AuthorizedSignatory;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.AuthorizedSignatoryMapper;
import com.cdl.escrow.repository.AuthorizedSignatoryRepository;
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
public class AuthorizedSignatoryCriteriaService extends BaseSpecificationBuilder<AuthorizedSignatory> implements Serializable {

    private final transient AuthorizedSignatoryRepository authorizedSignatoryRepository;

    private final transient AuthorizedSignatoryMapper authorizedSignatoryMapper;

    public Page<AuthorizedSignatoryDTO> findByCriteria(AuthorizedSignatoryCriteria criteria, Pageable pageable) {
        Specification<AuthorizedSignatory> specification = createSpecification(criteria);
        return authorizedSignatoryRepository.findAll(specification, pageable).map(authorizedSignatoryMapper::toDto);
    }

    private Specification<AuthorizedSignatory> createSpecification(AuthorizedSignatoryCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "customerCifNumber", criteria.getCustomerCifNumber(), true);
                addStringFilter(cb, root, predicates, "signatoryFullName", criteria.getSignatoryFullName(), true);
                addStringFilter(cb, root, predicates, "addressLine1", criteria.getAddressLine1(), true);
                addStringFilter(cb, root, predicates, "addressLine2", criteria.getAddressLine2(), true);
                addStringFilter(cb, root, predicates, "addressLine3", criteria.getAddressLine3(), true);
                addStringFilter(cb, root, predicates, "telephoneNumber", criteria.getTelephoneNumber(), true);
                addStringFilter(cb, root, predicates, "mobileNumber", criteria.getMobileNumber(), true);
                addStringFilter(cb, root, predicates, "emailAddress", criteria.getEmailAddress(), true);
                addStringFilter(cb, root, predicates, "notificationContactName", criteria.getNotificationContactName(), true);
                addStringFilter(cb, root, predicates, "emailAddress", criteria.getEmailAddress(), true);
                addStringFilter(cb, root, predicates, "notificationContactName", criteria.getNotificationContactName(), true);
                addStringFilter(cb, root, predicates, "signatoryCifNumber", criteria.getSignatoryCifNumber(), true);
                addStringFilter(cb, root, predicates, "notificationEmailAddress", criteria.getNotificationEmailAddress(), true);
                addStringFilter(cb, root, predicates, "notificationSignatureMimeType", criteria.getNotificationSignatureMimeType(), true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());

                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());

                // relationships

                if (criteria.getCifExistsId() != null) {
                    Join<Attachment, ApplicationSetting> join = root.join("cifExists", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getCifExistsId());
                }


            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
