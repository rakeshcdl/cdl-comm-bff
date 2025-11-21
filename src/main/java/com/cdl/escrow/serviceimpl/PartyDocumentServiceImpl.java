package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.PartyDocumentDTO;
import com.cdl.escrow.service.PartyDocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartyDocumentServiceImpl implements PartyDocumentService {
    @Override
    public Page<PartyDocumentDTO> getAllPartyDocument(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PartyDocumentDTO> getPartyDocumentById(Long id) {
        return Optional.empty();
    }

    @Override
    public PartyDocumentDTO savePartyDocument(PartyDocumentDTO partyDocumentDTO) {
        return null;
    }

    @Override
    public PartyDocumentDTO updatePartyDocument(Long id, PartyDocumentDTO partyDocumentDTO) {
        return null;
    }

    @Override
    public Boolean deletePartyDocumentById(Long id) {
        return null;
    }

    @Override
    public boolean softDeletePartyDocumentById(Long id) {
        return false;
    }
}
