package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTypeDTO implements Serializable {

    private Long id;

    private String labelCode;

    private String typeCode;

    private TaskStatusDTO taskStatusDTO;

    //private Set<AccountTypeCategory> childBankAccountTypes = new HashSet<>();

    //private Set<EscrowAccount> escrowAccounts = new HashSet<>();

    private Boolean enabled ;

    private Boolean deleted ;
}
