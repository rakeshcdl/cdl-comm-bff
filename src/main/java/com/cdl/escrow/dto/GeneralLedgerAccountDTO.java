package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralLedgerAccountDTO implements Serializable {

    private Long id;

    private String uuid;

    private String ledgerAccountNumber;

    private String branchIdentifierCode;

    private String ledgerAccountDescription;

    private String ledgerAccountTypeCode;   // e.g., ASSET, LIABILITY, INCOME, EXPENSE

    private Boolean active;               // lifecycle management flag

    private TaskStatusDTO taskStatusDTO;

    private Boolean enabled ;

    private Boolean deleted ;

}
