/**
 * AppLanguageTranslationCriteria.java
 *
 * @author Rakesh Raushan
 * @version 1.0
 * @description Escrow RERA application
 * @since 17/07/25
 */


package com.cdl.escrow.criteria;

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
public class AppLanguageTranslationCriteria implements Serializable {
    private LongFilter id;

    private StringFilter configId;

    private StringFilter configValue;

    private StringFilter content;

    //private LongFilter appLanguageCodeId;
}
