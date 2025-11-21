package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ReconciledTransactionDTO;
import com.cdl.escrow.entity.ReconciledTransaction;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReconciledTransactionMapper extends EntityMapper<ReconciledTransactionDTO, ReconciledTransaction> {

    ReconciledTransactionDTO toDto(ReconciledTransaction entity);

    ReconciledTransaction toEntity(ReconciledTransactionDTO dto);

}
