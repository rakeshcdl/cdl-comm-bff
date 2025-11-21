package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.PaymentInstructionDTO;
import com.cdl.escrow.entity.PaymentInstruction;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentInstructionMapper extends EntityMapper<PaymentInstructionDTO, PaymentInstruction> {

    PaymentInstructionDTO toDto(PaymentInstruction entity);

    PaymentInstruction toEntity(PaymentInstructionDTO dto);

}
