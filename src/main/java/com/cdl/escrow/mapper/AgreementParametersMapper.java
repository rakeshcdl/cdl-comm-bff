package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AgreementParametersDTO;
import com.cdl.escrow.entity.AgreementParameters;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgreementParametersMapper extends EntityMapper<AgreementParametersDTO, AgreementParameters> {

    AgreementParametersDTO toDto(AgreementParameters entity);

    AgreementParameters toEntity(AgreementParametersDTO dto);

}
