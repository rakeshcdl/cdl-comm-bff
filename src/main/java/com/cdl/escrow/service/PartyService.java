package com.cdl.escrow.service;

import com.cdl.escrow.dto.PartyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PartyService {

    Page<PartyDTO> getAllParty(final Pageable pageable);

    Optional<PartyDTO> getPartyById(Long id);

    PartyDTO saveParty(PartyDTO partyDTO);

    PartyDTO updateParty(Long id, PartyDTO partyDTO);

    Boolean deletePartyById(Long id);

    boolean softDeletePartyById(Long id);
}
