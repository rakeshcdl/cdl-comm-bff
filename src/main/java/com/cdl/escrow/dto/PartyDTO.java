package com.cdl.escrow.dto;

import com.cdl.escrow.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyDTO implements Serializable {

    private Long id;

    private String UUID;

    private String partyCifNumber;

    private String partyFullName;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String telephoneNumber;

    private String mobileNumber;

    private String emailAddress;

    private String bankIdentifier;

    private String passportIdentificationDetails;

    private String backupProjectAccountOwnerName;

    private String projectAccountOwnerName;

    private String assistantRelationshipManagerName;

    private String teamLeaderName;

    private String additionalRemarks;

    private String relationshipManagerName;

    private Boolean active;

    //private Set<AuthorizedSignatoryDTO> signatoriesDTO = new HashSet<>();

    //private Set<PartyDocumentDTO> documentsDTO = new HashSet<>();

    private ApplicationSettingDTO partyConstituentDTO;

    private ApplicationSettingDTO roleDTO;

    private TaskStatusDTO taskStatusDTO;

    //private Set<AgreementSignatory> agreementSignatories = new HashSet<>();

   // private Set<BulkPaymentUpload> bulkUploads = new HashSet<>();

    private Boolean enabled ;

    private Boolean deleted ;
}
