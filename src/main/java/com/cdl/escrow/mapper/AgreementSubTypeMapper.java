package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AgreementSubTypeDTO;
import com.cdl.escrow.entity.AgreementSubType;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgreementSubTypeMapper extends EntityMapper<AgreementSubTypeDTO, AgreementSubType> {

    AgreementSubTypeDTO toDto(AgreementSubType entity);

    AgreementSubType toEntity(AgreementSubTypeDTO dto);

}
