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
public class AuthorizedSignatoryCriteria implements Serializable {

    private LongFilter id;

    private StringFilter customerCifNumber;

    private StringFilter signatoryFullName;

    private StringFilter addressLine1;

    private StringFilter addressLine2;

    private StringFilter addressLine3;

    private StringFilter telephoneNumber;

    private StringFilter mobileNumber;

    private StringFilter emailAddress;

    private StringFilter notificationContactName;

    private StringFilter signatoryCifNumber;

    private StringFilter notificationEmailAddress;

    private StringFilter notificationSignatureMimeType;

    private BooleanFilter active;

    private LongFilter cifExistsId;

    private LongFilter partyId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
