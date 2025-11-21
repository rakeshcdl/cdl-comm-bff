package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ProductProgramDTO;
import com.cdl.escrow.entity.ProductProgram;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductProgramMapper extends EntityMapper<ProductProgramDTO, ProductProgram> {

    ProductProgramDTO toDto(ProductProgram entity);

    ProductProgram toEntity(ProductProgramDTO dto);

}
