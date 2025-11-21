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
public class GeneralLedgerAccountCriteria implements Serializable {

    private LongFilter id;

    private StringFilter uuid;

    private StringFilter ledgerAccountNumber;

    private StringFilter branchIdentifierCode;

    private StringFilter ledgerAccountDescription;

    private StringFilter ledgerAccountTypeCode;   // e.g., ASSET, LIABILITY, INCOME, EXPENSE

    private BooleanFilter active;               // lifecycle management flag

    private LongFilter taskStatusId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
