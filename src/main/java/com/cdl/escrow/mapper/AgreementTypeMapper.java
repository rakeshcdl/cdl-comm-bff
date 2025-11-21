package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AgreementTypeDTO;
import com.cdl.escrow.entity.AgreementType;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgreementTypeMapper extends EntityMapper<AgreementTypeDTO, AgreementType> {

    AgreementTypeDTO toDto(AgreementType entity);

    AgreementType toEntity(AgreementTypeDTO dto);

}
