package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTypeCategoryDTO implements Serializable {

    private Long id;

    private String labelCode;

    private String typeCode;

    private TaskStatusDTO taskStatusDTO;

    private AccountTypeDTO accountTypeDTO;

    //private Set<EscrowAccountDTO> escrowAccountSet = new HashSet<>();

    private Boolean enabled ;

    private Boolean deleted ;
}
