package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.PartyDocumentDTO;
import com.cdl.escrow.entity.PartyDocument;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.PartyDocumentMapper;
import com.cdl.escrow.repository.PartyDocumentRepository;
import com.cdl.escrow.service.PartyDocumentService;
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
public class PartyDocumentServiceImpl implements PartyDocumentService {

    private final PartyDocumentRepository repository;

    private final PartyDocumentMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<PartyDocumentDTO> getAllPartyDocument(Pageable pageable) {
        log.debug("Fetching all party document, page: {}", pageable.getPageNumber());
        Page<PartyDocument> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PartyDocumentDTO> getPartyDocumentById(Long id) {
        log.debug("Fetching party document with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public PartyDocumentDTO savePartyDocument(PartyDocumentDTO partyDocumentDTO) {
        log.info("Saving new party document");
        PartyDocument entity = mapper.toEntity(partyDocumentDTO);
        PartyDocument saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public PartyDocumentDTO updatePartyDocument(Long id, PartyDocumentDTO partyDocumentDTO) {
        log.info("Updating party document with ID: {}", id);

        PartyDocument existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("party document not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        PartyDocument toUpdate = mapper.toEntity(partyDocumentDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        PartyDocument updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deletePartyDocumentById(Long id) {
        log.info("Deleting party document with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("party document not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softDeletePartyDocumentById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
