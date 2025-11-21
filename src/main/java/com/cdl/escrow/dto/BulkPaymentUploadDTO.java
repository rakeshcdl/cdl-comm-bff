package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulkPaymentUploadDTO implements Serializable {

    private Long id;

    private String UUID;

    private Double paymentAmount;

    private String batchRemarks;

    private String utrReferenceNumber;

    private String transactionStatus;

    private String transactionRemarks;

    private Double exchangeRate;

    private Double convertedPaymentAmount;

    private String batchReferenceId;

    private String payerAddressLine1;

    private String payerAddressLine2;

    private String payerAddressLine3;

    private String transactionDateTime;

    private String paymentResponsePayload;

    private String sourceEntityType;

    private String documentName;

    private String recordValidationStatus;

    private String validationErrorMessage;

    private Boolean active;

    private PartyDTO partyDTO;

    private EscrowAgreementDTO escrowAgreementDTO;

    private EscrowAccountDTO escrowAccountDTO;

    private ApplicationSettingDTO transactionTypeDTO;

    private ApplicationSettingDTO currencyDTO;

    private TaskStatusDTO taskStatusDTO;

    private BeneficiaryDTO beneficiaryDTO;

    private Boolean enabled ;

    private Boolean deleted ;
}
