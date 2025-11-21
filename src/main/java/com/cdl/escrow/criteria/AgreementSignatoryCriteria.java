package com.cdl.escrow.criteria;

import com.cdl.escrow.dto.AuthorizedSignatoryDTO;
import com.cdl.escrow.dto.EscrowAgreementDTO;
import com.cdl.escrow.dto.PartyDTO;
import com.cdl.escrow.filter.BooleanFilter;
import com.cdl.escrow.filter.LongFilter;
import com.cdl.escrow.filter.StringFilter;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AgreementSignatoryCriteria implements Serializable {

    private LongFilter id;

    private StringFilter partyReferenceNumber;

    private StringFilter partyCustomerReferenceNumber;

    private StringFilter partyFullName;

    private StringFilter addressLine1;

    private StringFilter addressLine2;

    private StringFilter addressLine3;

    private StringFilter signatoryRole;

    private StringFilter notificationContactName;

    private StringFilter notificationAddressLine1;

    private StringFilter notificationAddressLine2;

    private StringFilter notificationAddressLine3;

    private StringFilter notificationEmailAddress;

    private StringFilter associationType;

    private BooleanFilter isEnabled;

    private LongFilter authorizedSignatoryId;

    private LongFilter partyId;

    private LongFilter escrowAgreementId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
