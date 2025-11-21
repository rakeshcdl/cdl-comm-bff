package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.EscrowAgreementDTO;
import com.cdl.escrow.entity.EscrowAgreement;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EscrowAgreementMapper extends EntityMapper<EscrowAgreementDTO, EscrowAgreement> {

    @Mapping(source = "clientName", target = "clientNameDTO")
    @Mapping(source = "businessSegment", target = "taskStatusDTO")
    @Mapping(source = "businessSubSegment", target = "businessSubSegmentDTO")
    @Mapping(source = "dealStatus", target = "dealStatusDTO")
    @Mapping(source = "fees", target = "feesDTO")
    @Mapping(source = "dealType", target = "dealTypeDTO")
    @Mapping(source = "dealSubType", target = "dealSubTypeDTO")
    @Mapping(source = "productProgram", target = "productProgramDTO")
    @Mapping(source = "dealPriority", target = "dealPriorityDTO")
    //@Mapping(source = "taskStatus", target = "taskStatusDTO")
    EscrowAgreementDTO toDto(EscrowAgreement entity);


    @Mapping(source = "clientNameDTO", target = "clientName")
    @Mapping(source = "businessSegmentDTO", target = "businessSegment")
    @Mapping(source = "businessSubSegmentDTO", target = "businessSubSegment")
    @Mapping(source = "dealStatusDTO", target = "dealStatus")
    @Mapping(source = "feesDTO", target = "fees")
    @Mapping(source = "dealTypeDTO", target = "dealType")
    @Mapping(source = "dealSubTypeDTO", target = "dealSubType")
    @Mapping(source = "productProgramDTO", target = "productProgram")
    @Mapping(source = "dealPriorityDTO", target = "dealPriority")
    //@Mapping(source = "taskStatusDTO", target = "taskStatus")
    EscrowAgreement toEntity(EscrowAgreementDTO dto);

}