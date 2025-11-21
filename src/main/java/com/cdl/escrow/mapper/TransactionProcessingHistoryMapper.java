package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.TransactionProcessingHistoryDTO;
import com.cdl.escrow.entity.TransactionProcessingHistory;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionProcessingHistoryMapper extends EntityMapper<TransactionProcessingHistoryDTO, TransactionProcessingHistory> {

    @Mapping(source = "status", target = "statusDTO")
    @Mapping(source = "standingInstructionBeneficiary", target = "standingInstructionBeneficiaryDTO")
    TransactionProcessingHistoryDTO toDto(TransactionProcessingHistory entity);

    @Mapping(source = "statusDTO", target = "status")
    @Mapping(source = "standingInstructionBeneficiaryDTO", target = "standingInstructionBeneficiary")
    TransactionProcessingHistory toEntity(TransactionProcessingHistoryDTO dto);

}
