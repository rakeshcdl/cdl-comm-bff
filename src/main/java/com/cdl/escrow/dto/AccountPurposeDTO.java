package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountPurposeDTO implements Serializable {

    private Long id;

    private String UUID;

    private String accountPurposeCode;

    private String accountPurposeName;

    private Boolean active;

    private ApplicationSettingDTO criticalityDTO;

    private TaskStatusDTO taskStatusDTO;

    //private Set<EscrowAccountDTO> escrowAccounts = new HashSet<>();

    private Boolean enabled ;

    private Boolean deleted ;
}
