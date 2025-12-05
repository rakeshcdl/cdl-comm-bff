package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.UnitaryPaymentInstructionDTO;
import com.cdl.escrow.entity.UnitaryPaymentInstruction;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UnitaryPaymentInstructionMapper extends EntityMapper<UnitaryPaymentInstructionDTO, UnitaryPaymentInstruction> {

    @Mapping(source = "dealNo", target = "dealNoDTO")
    @Mapping(source = "accountNo", target = "accountNoDTO")
    @Mapping(source = "paymentType", target = "paymentTypeDTO")
    @Mapping(source = "adhocPayment", target = "adhocPaymentDTO")
    @Mapping(source = "purpose", target = "purposeDTO")
    @Mapping(source = "beneficiaryCurrency", target = "beneficiaryCurrencyDTO")
    UnitaryPaymentInstructionDTO toDto(UnitaryPaymentInstruction entity);

    @Mapping(source = "dealNoDTO", target = "dealNo")
    @Mapping(source = "accountNoDTO", target = "accountNo")
    @Mapping(source = "paymentTypeDTO", target = "paymentType")
    @Mapping(source = "adhocPaymentDTO", target = "adhocPayment")
    @Mapping(source = "purposeDTO", target = "purpose")
    @Mapping(source = "beneficiaryCurrencyDTO", target = "beneficiaryCurrency")
    UnitaryPaymentInstruction toEntity(UnitaryPaymentInstructionDTO dto);

}
