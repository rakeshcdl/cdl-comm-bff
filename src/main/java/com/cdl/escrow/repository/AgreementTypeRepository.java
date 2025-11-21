package com.cdl.escrow.repository;

import com.cdl.escrow.entity.AgreementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgreementTypeRepository extends JpaRepository<AgreementType, Long>, JpaSpecificationExecutor<AgreementType> {

    Optional<AgreementType> findByIdAndDeletedFalse(Long id);
}
