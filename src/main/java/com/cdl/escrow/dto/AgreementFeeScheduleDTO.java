package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgreementFeeScheduleDTO implements Serializable {

    private Long id;

    private String UUID;

    private String regulatoryRefNo;

    private ZonedDateTime effectiveStartDate;

    private ZonedDateTime effectiveEndDate;

    private String operatingLocation;

    private String priorityLevel;

    private String transactionRateAmount;

    private String debitAccountNumber;

    private String creditAccountNumber;

    private Boolean active;

    private ApplicationSettingDTO feeDTO;

    private ApplicationSettingDTO feeTypeDTO;

    private ApplicationSettingDTO feesFrequencyDTO;

    private ApplicationSettingDTO frequencyBasisDTO;

    private AgreementTypeDTO agreementTypeDTO;

    private AgreementSubTypeDTO agreementSubTypeDTO;

    private ProductProgramDTO productProgramDTO;

    private EscrowAgreementDTO escrowAgreementDTO;

    private Boolean enabled ;

    private Boolean deleted ;
}
