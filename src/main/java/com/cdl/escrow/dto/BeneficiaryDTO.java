package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryDTO implements Serializable {

    private Long id;

    private String UUID;

    private String beneficiaryFullName;

    private String beneficiaryAddressLine1;

    private String telephoneNumber;

    private String mobileNumber;

    private String beneficiaryAccountNumber;

    private String bankIfscCode;

    private String beneficiaryBankName;

    private String bankRoutingCode;

    private String additionalRemarks;

    private Boolean active;

    private ApplicationSettingDTO accountTypeDTO;

    private ApplicationSettingDTO transferTypeDTO;

    private ApplicationSettingDTO roleDTO;

    private TaskStatusDTO taskStatusDTO;

    //private Set<PartyDocumentDTO> documents = new HashSet<>();

   // private Set<BulkPaymentUploadDTO> bulkUploads = new HashSet<>();

    private Boolean enabled ;

    private Boolean deleted ;
}
