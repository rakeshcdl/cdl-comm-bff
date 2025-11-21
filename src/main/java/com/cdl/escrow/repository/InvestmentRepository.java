package com.cdl.escrow.repository;

import com.cdl.escrow.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long>, JpaSpecificationExecutor<Investment> {

    Optional<Investment> findByIdAndDeletedFalse(Long id);
}

