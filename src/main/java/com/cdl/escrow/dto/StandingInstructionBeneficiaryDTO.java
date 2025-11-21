package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandingInstructionBeneficiaryDTO implements Serializable {

    private Long id;

    private String beneficiaryAccountNumber;

    private String beneficiaryBankIfscCode;

    private BigDecimal creditAmountCap;

    private BigDecimal creditAmount;

    private Integer transferPriorityLevel;

    private BigDecimal creditSharePercentage;

    private String currencyCode;          // ISO 4217 currency code

    private String paymentModeCode;       // e.g., NEFT, RTGS, SWIFT, INTERNAL


    //private Set<TransactionProcessingHistoryDTO> historiesDTO = new HashSet<>();

    private BeneficiaryDTO beneficiaryNameDTO;

    private ApplicationSettingDTO paymentModeDTO;

    private ApplicationSettingDTO currencyDTO;

    private StandingInstructionDTO standingInstructionDTO;

    private Boolean enabled ;

    private Boolean deleted ;
}
