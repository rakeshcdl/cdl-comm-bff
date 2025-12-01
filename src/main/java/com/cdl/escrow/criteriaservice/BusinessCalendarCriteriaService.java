package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.BusinessCalendarCriteria;
import com.cdl.escrow.dto.BusinessCalendarDTO;
import com.cdl.escrow.entity.ApplicationSetting;
import com.cdl.escrow.entity.BusinessCalendar;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.BusinessCalendarMapper;
import com.cdl.escrow.repository.BusinessCalendarRepository;
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
public class BusinessCalendarCriteriaService extends BaseSpecificationBuilder<BusinessCalendar> implements Serializable {

    private final transient BusinessCalendarRepository businessCalendarRepository;

    private final transient BusinessCalendarMapper businessCalendarMapper;

    public Page<BusinessCalendarDTO> findByCriteria(BusinessCalendarCriteria criteria, Pageable pageable) {
        Specification<BusinessCalendar> specification = createSpecification(criteria);
        return businessCalendarRepository.findAll(specification, pageable).map(businessCalendarMapper::toDto);
    }

    private Specification<BusinessCalendar> createSpecification(BusinessCalendarCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "uuid", criteria.getUuid(), true);
                addStringFilter(cb, root, predicates, "calendarName", criteria.getCalendarName(), true);
                addZonedDateTimeFilter(cb, root, predicates, "nonWorkingDate", criteria.getNonWorkingDate());
                addBooleanFilter(cb, root, predicates, "isEnabled", criteria.getIsEnabled());

                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());

                // relationships

                if (criteria.getDeductionToHappenId() != null) {
                    Join<BusinessCalendar, ApplicationSetting> join = root.join("deductionToHappen", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getDeductionToHappenId());
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
