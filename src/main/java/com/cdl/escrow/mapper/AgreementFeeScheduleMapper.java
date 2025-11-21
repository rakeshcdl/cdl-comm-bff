package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AgreementFeeScheduleDTO;
import com.cdl.escrow.entity.AgreementFeeSchedule;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgreementFeeScheduleMapper extends EntityMapper<AgreementFeeScheduleDTO, AgreementFeeSchedule> {

    AgreementFeeScheduleDTO toDto(AgreementFeeSchedule entity);

    AgreementFeeSchedule toEntity(AgreementFeeScheduleDTO dto);

}
