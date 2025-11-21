package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "schedule_job")
public class ScheduledJob implements Serializable {

    @Id
    @SequenceGenerator(
            name = "schedule_job_id_seq_gen",
            sequenceName = "schedule_job_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "schedule_job_seq_gen"
    )
    private Long id;

    private Integer executionSequenceNumber;

    private String scheduledJobIdentifier;

    private ZonedDateTime scheduledExecutionDateTime;

    private String jobStatusCode;         // e.g., PENDING, RUNNING, SUCCESS, FAILED

    private String triggeredBy;           // system/user ID that initiated the job

    private ZonedDateTime completedDateTime;

    private String remarks;               // free-text execution notes


    @OneToOne
    @JoinColumn(unique = true)
    private ProcessingStatus status;

    @ManyToOne
    @JsonIgnore
    private StandingInstruction standingInstruction;

    private Boolean enabled ;

    private Boolean deleted ;
}
