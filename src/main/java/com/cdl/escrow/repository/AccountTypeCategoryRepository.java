package com.cdl.escrow.repository;

import com.cdl.escrow.entity.AccountTypeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeCategoryRepository extends JpaRepository<AccountTypeCategory, Long>, JpaSpecificationExecutor<AccountTypeCategory> {

    Optional<AccountTypeCategory> findByIdAndDeletedFalse(Long id);
}
