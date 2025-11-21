package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.AuthorizedSignatoryDTO;
import com.cdl.escrow.entity.AuthorizedSignatory;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.AuthorizedSignatoryMapper;
import com.cdl.escrow.repository.AuthorizedSignatoryRepository;
import com.cdl.escrow.service.AuthorizedSignatoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizedSignatoryServiceImpl implements AuthorizedSignatoryService {

    private final AuthorizedSignatoryRepository repository;

    private final AuthorizedSignatoryMapper mapper;


    @Override
    public Page<AuthorizedSignatoryDTO> getAllAuthorizedSignatory(Pageable pageable) {
        log.debug("Fetching all application language code, page: {}", pageable.getPageNumber());
        Page<AuthorizedSignatory> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<AuthorizedSignatoryDTO> getAuthorizedSignatoryById(Long id) {
        log.debug("Fetching application language code with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public AuthorizedSignatoryDTO saveAuthorizedSignatory(AuthorizedSignatoryDTO authorizedSignatoryDTO) {
        log.info("Saving new application language code");
        AuthorizedSignatory entity = mapper.toEntity(authorizedSignatoryDTO);
        AuthorizedSignatory saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public AuthorizedSignatoryDTO updateAuthorizedSignatory(Long id, AuthorizedSignatoryDTO authorizedSignatoryDTO) {
        log.info("Updating application language code with ID: {}", id);

        AuthorizedSignatory existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("language code not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AuthorizedSignatory toUpdate = mapper.toEntity(authorizedSignatoryDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AuthorizedSignatory updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteAuthorizedSignatoryById(Long id) {
        log.info("Deleting application language code with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("language code not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean softDeleteAuthorizedSignatoryById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
