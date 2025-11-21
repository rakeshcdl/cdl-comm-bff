package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BulkPaymentUploadDTO;
import com.cdl.escrow.entity.BulkPaymentUpload;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BulkPaymentUploadMapper extends EntityMapper<BulkPaymentUploadDTO, BulkPaymentUpload> {

    BulkPaymentUploadDTO toDto(BulkPaymentUpload entity);

    BulkPaymentUpload toEntity(BulkPaymentUploadDTO dto);

}
