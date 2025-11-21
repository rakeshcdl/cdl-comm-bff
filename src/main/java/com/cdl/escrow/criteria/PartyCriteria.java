package com.cdl.escrow.criteria;

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
public class PartyCriteria implements Serializable {

    private LongFilter id;

    private StringFilter UUID;

    private StringFilter partyCifNumber;

    private StringFilter partyFullName;

    private StringFilter addressLine1;

    private StringFilter addressLine2;

    private StringFilter addressLine3;

    private StringFilter telephoneNumber;

    private StringFilter mobileNumber;

    private StringFilter emailAddress;

    private StringFilter bankIdentifier;

    private StringFilter passportIdentificationDetails;

    private StringFilter backupProjectAccountOwnerName;

    private StringFilter projectAccountOwnerName;

    private StringFilter assistantRelationshipManagerName;

    private StringFilter teamLeaderName;

    private StringFilter additionalRemarks;

    private StringFilter relationshipManagerName;

    private BooleanFilter active;

    private LongFilter partyConstituentId;

    private LongFilter roleId;

    private LongFilter taskStatusId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
