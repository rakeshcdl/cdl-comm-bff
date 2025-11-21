package com.cdl.escrow.repository;

import com.cdl.escrow.entity.AgreementParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgreementParametersRepository extends JpaRepository<AgreementParameters, Long>, JpaSpecificationExecutor<AgreementParameters> {

    Optional<AgreementParameters> findByIdAndDeletedFalse(Long id);
}