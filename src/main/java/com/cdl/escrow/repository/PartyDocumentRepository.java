package com.cdl.escrow.repository;

import com.cdl.escrow.entity.PartyDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartyDocumentRepository extends JpaRepository<PartyDocument, Long>, JpaSpecificationExecutor<PartyDocument> {

    Optional<PartyDocument> findByIdAndDeletedFalse(Long id);
}

