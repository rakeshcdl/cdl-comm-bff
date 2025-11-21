package com.cdl.escrow.repository;

import com.cdl.escrow.entity.ProductProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductProgramRepository extends JpaRepository<ProductProgram, Long>, JpaSpecificationExecutor<ProductProgram> {

    Optional<ProductProgram> findByIdAndDeletedFalse(Long id);
}

