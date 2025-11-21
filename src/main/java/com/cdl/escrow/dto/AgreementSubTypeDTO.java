package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgreementSubTypeDTO implements Serializable {

    private Long id;

    private String UUID;

    private String subTypeName;

    private String subTypeDescription;

    private Boolean active;

    private AgreementTypeDTO agreementTypeDTO;

    private TaskStatusDTO taskStatusDTO;

    private Boolean enabled ;

    private Boolean deleted ;
}
