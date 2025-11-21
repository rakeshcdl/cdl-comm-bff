package com.cdl.escrow.repository;

import com.cdl.escrow.entity.GeneralLedgerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneralLedgerAccountRepository extends JpaRepository<GeneralLedgerAccount, Long>, JpaSpecificationExecutor<GeneralLedgerAccount> {

    Optional<GeneralLedgerAccount> findByIdAndDeletedFalse(Long id);
}

