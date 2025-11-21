package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ProductProgramDTO;
import com.cdl.escrow.entity.ProductProgram;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductProgramMapper extends EntityMapper<ProductProgramDTO, ProductProgram> {

    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    ProductProgramDTO toDto(ProductProgram entity);

    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    ProductProgram toEntity(ProductProgramDTO dto);

}
