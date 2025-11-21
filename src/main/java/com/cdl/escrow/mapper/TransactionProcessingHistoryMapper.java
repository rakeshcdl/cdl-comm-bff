package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.TransactionProcessingHistoryDTO;
import com.cdl.escrow.entity.TransactionProcessingHistory;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionProcessingHistoryMapper extends EntityMapper<TransactionProcessingHistoryDTO, TransactionProcessingHistory> {

    TransactionProcessingHistoryDTO toDto(TransactionProcessingHistory entity);

    TransactionProcessingHistory toEntity(TransactionProcessingHistoryDTO dto);

}
