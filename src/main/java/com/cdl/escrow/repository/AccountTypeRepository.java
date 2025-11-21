package com.cdl.escrow.repository;

import com.cdl.escrow.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long>, JpaSpecificationExecutor<AccountType> {

    Optional<AccountType> findByIdAndDeletedFalse(Long id);
}

