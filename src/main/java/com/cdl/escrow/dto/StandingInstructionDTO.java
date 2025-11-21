package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandingInstructionDTO implements Serializable {

    private Long id;

    private String standingInstructionReferenceNumber;

    private String clientFullName;

    private BigDecimal debitAmountCap;

    private BigDecimal debitAmount;

    private BigDecimal minimumBalanceAmount;

    private BigDecimal thresholdAmount;

    private ZonedDateTime firstTransactionDateTime;

    private ZonedDateTime instructionExpiryDateTime;

    private Integer retryIntervalDays;

    private Boolean retryUntilMonthEndFlag;

    private String instructionRemarks;

    private ZonedDateTime nextExecutionDateTime;

    //private Set<ScheduledJobDTO> scheduledJobsDTO = new HashSet<>();

    //private Set<StandingInstructionBeneficiaryDTO> siPaymentBeneficiariesDTO = new HashSet<>();

    private EscrowAgreementDTO dealNoDTO;

    private ApplicationSettingDTO statusDTO;

    private ApplicationSettingDTO transferTypeDTO;

    private ApplicationSettingDTO occurrenceDTO;

    private ApplicationSettingDTO recurringFrequencyDTO;

    private ApplicationSettingDTO holidaySetupDTO;

    private ApplicationSettingDTO dependentScenarioDTO;

    private EscrowAccountDTO formAccountDrDTO;

    private StandingInstructionDTO dependenceDTO;

    private TaskStatusDTO taskStatusDTO;

    private Boolean enabled ;

    private Boolean deleted ;
}
