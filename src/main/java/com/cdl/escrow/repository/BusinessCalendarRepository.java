package com.cdl.escrow.repository;

import com.cdl.escrow.entity.BusinessCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessCalendarRepository extends JpaRepository<BusinessCalendar, Long>, JpaSpecificationExecutor<BusinessCalendar> {

    Optional<BusinessCalendar> findByIdAndDeletedFalse(Long id);
}