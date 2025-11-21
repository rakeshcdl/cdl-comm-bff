package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.UnreconciledTransactionDTO;
import com.cdl.escrow.entity.UnreconciledTransaction;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnreconciledTransactionMapper extends EntityMapper<UnreconciledTransactionDTO, UnreconciledTransaction> {

    UnreconciledTransactionDTO toDto(UnreconciledTransaction entity);

    UnreconciledTransaction toEntity(UnreconciledTransactionDTO dto);

}
