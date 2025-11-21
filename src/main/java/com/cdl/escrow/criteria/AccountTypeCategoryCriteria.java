package com.cdl.escrow.criteria;

import com.cdl.escrow.dto.AccountTypeDTO;
import com.cdl.escrow.dto.TaskStatusDTO;
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
public class AccountTypeCategoryCriteria implements Serializable {

    private LongFilter id;

    private StringFilter labelCode;

    private StringFilter typeCode;

    private LongFilter taskStatusId;

    private LongFilter accountTypeId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
