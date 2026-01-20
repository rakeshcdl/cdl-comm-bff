package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AgreementFeeScheduleDTO;
import com.cdl.escrow.entity.AgreementFeeSchedule;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgreementFeeScheduleMapper extends EntityMapper<AgreementFeeScheduleDTO, AgreementFeeSchedule> {

    @Mapping(source = "fee", target = "feeDTO")
    @Mapping(source = "feeType", target = "feeTypeDTO")
    @Mapping(source = "feesFrequency", target = "feesFrequencyDTO")
    @Mapping(source = "frequencyBasis", target = "frequencyBasisDTO")
    @Mapping(source = "agreementType", target = "agreementTypeDTO")
    @Mapping(source = "agreementSubType", target = "agreementSubTypeDTO")
    @Mapping(source = "productProgram", target = "productProgramDTO")
    @Mapping(source = "escrowAgreement", target = "escrowAgreementDTO")
    AgreementFeeScheduleDTO toDto(AgreementFeeSchedule entity);


    @Mapping(source = "feeDTO", target = "fee")
    @Mapping(source = "feeTypeDTO", target = "feeType")
    @Mapping(source = "feesFrequencyDTO", target = "feesFrequency")
    @Mapping(source = "frequencyBasisDTO", target = "frequencyBasis")
    @Mapping(source = "agreementTypeDTO", target = "agreementType")
    @Mapping(source = "agreementSubTypeDTO", target = "agreementSubType")
    @Mapping(source = "productProgramDTO", target = "productProgram")
    @Mapping(source = "escrowAgreementDTO", target = "escrowAgreement")
    AgreementFeeSchedule toEntity(AgreementFeeScheduleDTO dto);

}
