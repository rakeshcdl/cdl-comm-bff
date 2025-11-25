package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.PartyCriteria;
import com.cdl.escrow.dto.PartyDTO;
import com.cdl.escrow.entity.ApplicationSetting;
import com.cdl.escrow.entity.Party;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.PartyMapper;
import com.cdl.escrow.repository.PartyRepository;
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
public class PartyCriteriaService extends BaseSpecificationBuilder<Party> implements Serializable {

    private final transient PartyRepository partyRepository;

    private final transient PartyMapper partyMapper;

    public Page<PartyDTO> findByCriteria(PartyCriteria criteria, Pageable pageable) {
        Specification<Party> specification = createSpecification(criteria);
        return partyRepository.findAll(specification, pageable).map(partyMapper::toDto);
    }

    private Specification<Party> createSpecification(PartyCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "UUID", criteria.getUUID(), true);
                addStringFilter(cb, root, predicates, "partyCifNumber", criteria.getPartyCifNumber(), true);
                addStringFilter(cb, root, predicates, "partyFullName", criteria.getPartyFullName(), true);

                addStringFilter(cb, root, predicates, "addressLine1", criteria.getAddressLine1(), true);
                addStringFilter(cb, root, predicates, "addressLine2", criteria.getAddressLine2(), true);
                addStringFilter(cb, root, predicates, "addressLine3", criteria.getAddressLine3(), true);
                addStringFilter(cb, root, predicates, "telephoneNumber", criteria.getTelephoneNumber(), true);
                addStringFilter(cb, root, predicates, "mobileNumber", criteria.getMobileNumber(), true);
                addStringFilter(cb, root, predicates, "emailAddress", criteria.getEmailAddress(), true);
                addStringFilter(cb, root, predicates, "bankIdentifier", criteria.getBankIdentifier(), true);
                addStringFilter(cb, root, predicates, "passportIdentificationDetails", criteria.getPassportIdentificationDetails(), true);
                addStringFilter(cb, root, predicates, "backupProjectAccountOwnerName", criteria.getBackupProjectAccountOwnerName(), true);

                addStringFilter(cb, root, predicates, "projectAccountOwnerName", criteria.getProjectAccountOwnerName(), true);
                addStringFilter(cb, root, predicates, "assistantRelationshipManagerName", criteria.getAssistantRelationshipManagerName(), true);
                addStringFilter(cb, root, predicates, "teamLeaderName", criteria.getTeamLeaderName(), true);
                addStringFilter(cb, root, predicates, "additionalRemarks", criteria.getAdditionalRemarks(), true);
                addStringFilter(cb, root, predicates, "relationshipManagerName", criteria.getRelationshipManagerName(), true);
                addBooleanFilter(cb, root, predicates, "active", criteria.getActive());


                // relationships

                if (criteria.getPartyConstituentId() != null) {
                    Join<Party, ApplicationSetting> join = root.join("partyConstituent", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getPartyConstituentId());
                }

                if (criteria.getRoleId() != null) {
                    Join<Party, ApplicationSetting> join = root.join("role", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getRoleId());
                }


            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
