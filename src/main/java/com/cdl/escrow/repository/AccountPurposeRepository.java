package com.cdl.escrow.repository;


import com.cdl.escrow.entity.AccountPurpose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountPurposeRepository extends JpaRepository<AccountPurpose, Long>, JpaSpecificationExecutor<AccountPurpose> {

    Optional<AccountPurpose> findByIdAndDeletedFalse(Long id);
}
