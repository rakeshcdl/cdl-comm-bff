package com.cdl.escrow.dto;

import com.cdl.escrow.enumeration.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessingStatusDTO implements Serializable {

    private Long id;

    private Long previousJobReferenceId;
    private Long currentJobReferenceId;
    private Long nextJobReferenceId;
    private String processDetails;
    private String statusMessage;
    private ZonedDateTime createdDateTime;

    private TransactionStatus status;

    private Boolean enabled ;

    private Boolean deleted ;

}
