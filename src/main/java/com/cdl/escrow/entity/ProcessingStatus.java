package com.cdl.escrow.entity;

import com.cdl.escrow.enumeration.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.ZonedDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Jacksonized
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "processing_status")
public class ProcessingStatus implements Serializable {

    @Id
    @SequenceGenerator(
            name = "processing_status_id_seq_gen",
            sequenceName = "processing_status_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "processing_status_seq_gen"
    )
    private Long id;

    private Long previousJobReferenceId;
    private Long currentJobReferenceId;
    private Long nextJobReferenceId;
    private String processDetails;
    private String statusMessage;
    private ZonedDateTime createdDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransactionStatus status;

    private Boolean enabled ;

    private Boolean deleted ;
}
