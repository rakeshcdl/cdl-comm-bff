package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AgreementTypeDTO;
import com.cdl.escrow.entity.AgreementType;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgreementTypeMapper extends EntityMapper<AgreementTypeDTO, AgreementType> {

    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    AgreementTypeDTO toDto(AgreementType entity);

    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    AgreementType toEntity(AgreementTypeDTO dto);

}
