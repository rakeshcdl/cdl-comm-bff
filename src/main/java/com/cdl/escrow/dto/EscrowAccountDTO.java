package com.cdl.escrow.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EscrowAccountDTO implements Serializable {

    private Long id;

    private String UUID;

    private String accountNumber;

    private String productCode;           // or productCodeIdentifier

    private String accountDisplayName;

    private String ibanNumber;

    private String officialAccountTitle;

    private String virtualAccountNumber;

    private String accountTypeCode;

    private String assignmentStatus;      // or Boolean assignedFlag

    private String assignedToReference;

    private ZonedDateTime accountOpenDateTime;

    private String referenceField1;

    private String referenceField2;

    private Boolean active;

    private ApplicationSettingDTO taxPaymentDTO;

    private ApplicationSettingDTO currencyDTO;

    private AccountPurposeDTO accountPurposeDTO;

    private ApplicationSettingDTO accountCategoryDTO;

    private ApplicationSettingDTO primaryAccountDTO;

    private ApplicationSettingDTO bulkUploadProcessingDTO;

    private ApplicationSettingDTO unitaryPaymentDTO;

    private AccountTypeDTO accountTypeDTO;

    private AccountTypeCategoryDTO accountTypeCategoryDTO;

    private EscrowAgreementDTO escrowAgreementDTO;

   // private Set<ReconciledTransaction> reconTransactions = new HashSet<>();

    //private Set<UnreconciledTransaction> nonReconTransactions = new HashSet<>();

   // private Set<BulkPaymentUpload> bulkUploads = new HashSet<>();

    private Boolean enabled ;

    private Boolean deleted ;
}
