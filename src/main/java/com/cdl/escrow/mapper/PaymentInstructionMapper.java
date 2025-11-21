package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.PaymentInstructionDTO;
import com.cdl.escrow.entity.PaymentInstruction;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentInstructionMapper extends EntityMapper<PaymentInstructionDTO, PaymentInstruction> {

    @Mapping(source = "paymentType", target = "paymentTypeDTO")
    @Mapping(source = "adhocPayment", target = "adhocPaymentDTO")
    @Mapping(source = "purpose", target = "purposeDTO")
    @Mapping(source = "beneficiaryCurrency", target = "beneficiaryCurrencyDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    PaymentInstructionDTO toDto(PaymentInstruction entity);

    @Mapping(source = "paymentTypeDTO", target = "paymentType")
    @Mapping(source = "adhocPaymentDTO", target = "adhocPayment")
    @Mapping(source = "purposeDTO", target = "purpose")
    @Mapping(source = "beneficiaryCurrencyDTO", target = "beneficiaryCurrency")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    PaymentInstruction toEntity(PaymentInstructionDTO dto);

}
