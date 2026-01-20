package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgreementParametersDTO implements Serializable {

    private Long id;

    private String UUID;

    private String parametersRefNo;

    private ZonedDateTime agreementEffectiveDate;

    private ZonedDateTime agreementExpiryDate;

    private String agreementRemarks;

    private Boolean active;

    private ApplicationSettingDTO permittedInvestmentAllowedDTO;

    private ApplicationSettingDTO amendmentAllowedDTO;

    private ApplicationSettingDTO dealClosureBasisDTO;

    private EscrowAgreementDTO escrowAgreementDTO;

    private Boolean enabled ;

    private Boolean deleted ;
}
