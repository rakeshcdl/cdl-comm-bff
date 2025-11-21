package com.cdl.escrow.repository;

import com.cdl.escrow.entity.AgreementFeeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgreementFeeScheduleRepository extends JpaRepository<AgreementFeeSchedule, Long>, JpaSpecificationExecutor<AgreementFeeSchedule> {

    Optional<AgreementFeeSchedule> findByIdAndDeletedFalse(Long id);
}

