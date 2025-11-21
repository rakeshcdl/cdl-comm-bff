package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name = "attachment")
public class Attachment {

    @Id
    @SequenceGenerator(
            name = "attachment_id_seq_gen",
            sequenceName = "attachment_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "attachment_seq_gen"
    )
    private Long id;

    private String name;

    private String location;

    private String module;

    private Long recordId;

    private String storageType;

    private ZonedDateTime uploadDate;

    private String documentSize;

    private ZonedDateTime validityDate;

    @Lob
    @Column(name = "event_detail")
    private String eventDetail;

    @ManyToOne
    @JsonIgnore
    private PartyDocument partyDocument;

    @ManyToOne
    private ApplicationSetting documentType;

    private Boolean enabled ;

    private Boolean deleted ;
}
