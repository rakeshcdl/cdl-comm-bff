package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AgreementSignatoryDTO;
import com.cdl.escrow.entity.AgreementSignatory;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgreementSignatoryMapper extends EntityMapper<AgreementSignatoryDTO, AgreementSignatory> {

    AgreementSignatoryDTO toDto(AgreementSignatory entity);

    AgreementSignatory toEntity(AgreementSignatoryDTO dto);

}