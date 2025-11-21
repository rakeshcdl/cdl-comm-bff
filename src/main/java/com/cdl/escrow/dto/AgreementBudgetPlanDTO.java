package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgreementBudgetPlanDTO {

    private Long id;

    private String expenseMetadataJson;

    private EscrowAgreementDTO escrowAgreementDTO;

    private Boolean enabled ;

    private Boolean deleted ;

}
