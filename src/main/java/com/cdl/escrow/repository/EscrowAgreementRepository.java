package com.cdl.escrow.repository;

import com.cdl.escrow.entity.EscrowAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EscrowAgreementRepository extends JpaRepository<EscrowAgreement, Long>, JpaSpecificationExecutor<EscrowAgreement> {

    Optional<EscrowAgreement> findByIdAndDeletedFalse(Long id);
}
