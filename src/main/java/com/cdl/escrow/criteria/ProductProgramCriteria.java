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
public class ProductProgramCriteria implements Serializable {

    private LongFilter id;

    private StringFilter UUID;

    private StringFilter programName;

    private StringFilter programDescription;

    private BooleanFilter active;

    private LongFilter taskStatusId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted ;
}
