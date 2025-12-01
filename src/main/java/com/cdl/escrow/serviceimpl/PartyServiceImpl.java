package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.PartyDTO;
import com.cdl.escrow.entity.Party;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.PartyMapper;
import com.cdl.escrow.repository.PartyRepository;
import com.cdl.escrow.service.PartyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartyServiceImpl implements PartyService {

    private final PartyRepository repository;

    private final PartyMapper mapper;



    @Override
    @Transactional(readOnly = true)
    public Page<PartyDTO> getAllParty(Pageable pageable) {
        log.debug("Fetching all party, page: {}", pageable.getPageNumber());
        Page<Party> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PartyDTO> getPartyById(Long id) {
        log.debug("Fetching party with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public PartyDTO saveParty(PartyDTO partyDTO) {
        log.info("Saving new party");
        Party entity = mapper.toEntity(partyDTO);
        Party saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public PartyDTO updateParty(Long id, PartyDTO partyDTO) {
        log.info("Updating party with ID: {}", id);

        Party existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("party not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        Party toUpdate = mapper.toEntity(partyDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        Party updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deletePartyById(Long id) {
        log.info("Deleting party with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("party not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeletePartyById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
