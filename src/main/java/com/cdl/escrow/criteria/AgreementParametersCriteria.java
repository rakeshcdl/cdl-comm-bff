package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.BooleanFilter;
import com.cdl.escrow.filter.LongFilter;
import com.cdl.escrow.filter.StringFilter;
import com.cdl.escrow.filter.ZonedDateTimeFilter;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AgreementParametersCriteria implements Serializable {


    private LongFilter id;

    private StringFilter UUID;

    private StringFilter parametersRefNo;

    private ZonedDateTimeFilter agreementEffectiveDate;

    private ZonedDateTimeFilter agreementExpiryDate;

    private StringFilter agreementRemarks;

    private BooleanFilter active;

    private LongFilter permittedInvestmentAllowedId;

    private LongFilter amendmentAllowedId;

    private LongFilter dealClosureBasisId;

    private LongFilter escrowAgreementId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;

}
