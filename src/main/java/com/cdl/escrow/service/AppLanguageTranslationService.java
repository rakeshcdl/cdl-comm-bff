/**
 * AppLanguageTranslationService.java
 *
 * @author Rakesh Raushan
 * @version 1.0
 * @description Escrow RERA application
 * @since 22/07/25
 */

package com.cdl.escrow.service;

import com.cdl.escrow.dto.AppLanguageTranslationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface AppLanguageTranslationService {

    Page<AppLanguageTranslationDTO> getAllAppLanguageTranslation(final Pageable pageable);

    Optional<AppLanguageTranslationDTO> getAppLanguageTranslationById(Long id);

    AppLanguageTranslationDTO saveAppLanguageTranslation(AppLanguageTranslationDTO appLanguageTranslationDTO);

    AppLanguageTranslationDTO updateAppLanguageTranslation(Long id, AppLanguageTranslationDTO appLanguageTranslationDTO);

    Boolean deleteAppLanguageTranslationById(Long id);

    List<AppLanguageTranslationDTO> getNavMenuAppLanguageTranslations();

    List<AppLanguageTranslationDTO> getAllAppLanguageTranslationData();

    List<AppLanguageTranslationDTO> getTransactionsAppLanguageTranslationData();

    List<AppLanguageTranslationDTO> getPaymentsAppLanguageTranslationData();

    boolean softDeleteAppLanguageTranslationById(Long id);

    List<AppLanguageTranslationDTO> getUserAppLanguageTranslationData();

    List<AppLanguageTranslationDTO> getRoleAppLanguageTranslationData();

    List<AppLanguageTranslationDTO> getGroupAppLanguageTranslationData();

    List<AppLanguageTranslationDTO> getReportTranslationData();

    List<AppLanguageTranslationDTO> getDashboardTranslationData();

    List<AppLanguageTranslationDTO> getEntitiesAppLanguageTranslationData();

    List<AppLanguageTranslationDTO> getMastersAppLanguageTranslationData();

    List<AppLanguageTranslationDTO> getCommonAppLanguageTranslationData();
}
