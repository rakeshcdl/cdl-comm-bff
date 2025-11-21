package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.PartyDTO;
import com.cdl.escrow.service.PartyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartyServiceImpl implements PartyService {
    @Override
    public Page<PartyDTO> getAllParty(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PartyDTO> getPartyById(Long id) {
        return Optional.empty();
    }

    @Override
    public PartyDTO saveParty(PartyDTO partyDTO) {
        return null;
    }

    @Override
    public PartyDTO updateParty(Long id, PartyDTO partyDTO) {
        return null;
    }

    @Override
    public Boolean deletePartyById(Long id) {
        return null;
    }

    @Override
    public boolean softDeletePartyById(Long id) {
        return false;
    }
}
