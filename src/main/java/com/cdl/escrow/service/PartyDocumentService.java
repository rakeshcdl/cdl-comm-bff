package com.cdl.escrow.service;

import com.cdl.escrow.dto.PartyDocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PartyDocumentService {

    Page<PartyDocumentDTO> getAllPartyDocument(final Pageable pageable);

    Optional<PartyDocumentDTO> getPartyDocumentById(Long id);

    PartyDocumentDTO savePartyDocument(PartyDocumentDTO partyDocumentDTO);

    PartyDocumentDTO updatePartyDocument(Long id, PartyDocumentDTO partyDocumentDTO);

    Boolean deletePartyDocumentById(Long id);

    boolean softDeletePartyDocumentById(Long id);
}
