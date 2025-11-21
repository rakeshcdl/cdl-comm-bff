package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgreementTypeDTO implements Serializable {

    private Long id;

    private String uuid;

    private String agreementTypeName;

    private String agreementTypeDescription;

    private Boolean active;

    private TaskStatusDTO taskStatusDTO;

    private Boolean enabled ;

    private Boolean deleted ;

}
