package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.EscrowAccountDTO;
import com.cdl.escrow.entity.EscrowAccount;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EscrowAccountMapper extends EntityMapper<EscrowAccountDTO, EscrowAccount> {

    @Mapping(source = "taxPayment", target = "taxPaymentDTO")
    @Mapping(source = "currency", target = "currencyDTO")
    @Mapping(source = "accountPurpose", target = "accountPurposeDTO")
    @Mapping(source = "accountCategory", target = "accountCategoryDTO")
    @Mapping(source = "primaryAccount", target = "primaryAccountDTO")
    @Mapping(source = "bulkUploadProcessing", target = "bulkUploadProcessingDTO")
    @Mapping(source = "unitaryPayment", target = "unitaryPaymentDTO")
    @Mapping(source = "accountType", target = "accountTypeDTO")
    @Mapping(source = "accountTypeCategory", target = "accountTypeCategoryDTO")
    @Mapping(source = "escrowAgreement", target = "escrowAgreementDTO")
    EscrowAccountDTO toDto(EscrowAccount entity);

    @Mapping(source = "taxPaymentDTO", target = "taxPayment")
    @Mapping(source = "currencyDTO", target = "currency")
    @Mapping(source = "accountPurposeDTO", target = "accountPurpose")
    @Mapping(source = "accountCategoryDTO", target = "accountCategory")
    @Mapping(source = "primaryAccountDTO", target = "primaryAccount")
    @Mapping(source = "bulkUploadProcessingDTO", target = "bulkUploadProcessing")
    @Mapping(source = "unitaryPaymentDTO", target = "unitaryPayment")
    @Mapping(source = "accountTypeDTO", target = "accountType")
    @Mapping(source = "accountTypeCategoryDTO", target = "accountTypeCategory")
    @Mapping(source = "escrowAgreementDTO", target = "escrowAgreement")
    EscrowAccount toEntity(EscrowAccountDTO dto);

}