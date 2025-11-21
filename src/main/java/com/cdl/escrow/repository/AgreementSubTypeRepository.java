package com.cdl.escrow.repository;

import com.cdl.escrow.entity.AgreementSubType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgreementSubTypeRepository extends JpaRepository<AgreementSubType, Long>, JpaSpecificationExecutor<AgreementSubType> {

    Optional<AgreementSubType> findByIdAndDeletedFalse(Long id);
}
