package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AgreementSubTypeDTO;
import com.cdl.escrow.entity.AgreementSubType;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgreementSubTypeMapper extends EntityMapper<AgreementSubTypeDTO, AgreementSubType> {

    @Mapping(source = "agreementType", target = "agreementTypeDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    AgreementSubTypeDTO toDto(AgreementSubType entity);

    @Mapping(source = "agreementTypeDTO", target = "agreementType")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    AgreementSubType toEntity(AgreementSubTypeDTO dto);

}
