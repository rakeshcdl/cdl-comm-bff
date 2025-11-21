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
public class BeneficiaryCriteria implements Serializable {

    private LongFilter id;

    private StringFilter UUID;

    private StringFilter beneficiaryFullName;

    private StringFilter beneficiaryAddressLine1;

    private StringFilter telephoneNumber;

    private StringFilter mobileNumber;

    private StringFilter beneficiaryAccountNumber;

    private StringFilter bankIfscCode;

    private StringFilter beneficiaryBankName;

    private StringFilter bankRoutingCode;

    private StringFilter additionalRemarks;

    private BooleanFilter active;

    private LongFilter accountTypeId;

    private LongFilter transferTypeId;

    private LongFilter roleId;

    private LongFilter taskStatusId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
