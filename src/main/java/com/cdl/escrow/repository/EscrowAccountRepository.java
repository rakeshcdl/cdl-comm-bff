package com.cdl.escrow.repository;

import com.cdl.escrow.entity.EscrowAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EscrowAccountRepository extends JpaRepository<EscrowAccount, Long>, JpaSpecificationExecutor<EscrowAccount> {

    Optional<EscrowAccount> findByIdAndDeletedFalse(Long id);
}

