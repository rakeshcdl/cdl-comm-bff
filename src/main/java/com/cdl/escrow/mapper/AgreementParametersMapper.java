package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AgreementParametersDTO;
import com.cdl.escrow.entity.AgreementParameters;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgreementParametersMapper extends EntityMapper<AgreementParametersDTO, AgreementParameters> {

    @Mapping(source = "permittedInvestmentAllowed", target = "permittedInvestmentAllowedDTO")
    @Mapping(source = "amendmentAllowed", target = "amendmentAllowedDTO")
    @Mapping(source = "dealClosureBasis", target = "dealClosureBasisDTO")
    @Mapping(source = "escrowAgreement", target = "escrowAgreementDTO")
    AgreementParametersDTO toDto(AgreementParameters entity);

    @Mapping(source = "permittedInvestmentAllowedDTO", target = "permittedInvestmentAllowed")
    @Mapping(source = "amendmentAllowedDTO", target = "amendmentAllowed")
    @Mapping(source = "dealClosureBasisDTO", target = "dealClosureBasis")
    @Mapping(source = "escrowAgreementDTO", target = "escrowAgreement")
    AgreementParameters toEntity(AgreementParametersDTO dto);

}
