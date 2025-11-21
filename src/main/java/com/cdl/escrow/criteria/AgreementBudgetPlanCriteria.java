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
public class AgreementBudgetPlanCriteria implements Serializable {

    private LongFilter id;

    private StringFilter expenseMetadataJson;

    private LongFilter escrowAgreementId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
