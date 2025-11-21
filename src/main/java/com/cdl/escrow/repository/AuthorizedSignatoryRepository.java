package com.cdl.escrow.repository;

import com.cdl.escrow.entity.AuthorizedSignatory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizedSignatoryRepository  extends JpaRepository<AuthorizedSignatory, Long>, JpaSpecificationExecutor<AuthorizedSignatory> {

    Optional<AuthorizedSignatory> findByIdAndDeletedFalse(Long id);
}