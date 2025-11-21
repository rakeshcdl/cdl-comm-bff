package com.cdl.escrow.repository;

import com.cdl.escrow.entity.AgreementSignatory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgreementSignatoryRepository  extends JpaRepository<AgreementSignatory, Long>, JpaSpecificationExecutor<AgreementSignatory> {

    Optional<AgreementSignatory> findByIdAndDeletedFalse(Long id);
}
