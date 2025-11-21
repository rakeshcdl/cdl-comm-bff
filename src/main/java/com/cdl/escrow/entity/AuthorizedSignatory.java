package com.cdl.escrow.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Jacksonized
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "authorized_signatory")
public class AuthorizedSignatory implements Serializable {

    // this is rename of Signatory
    @Id
    @SequenceGenerator(
            name = "authorized_signatory_id_seq_gen",
            sequenceName = "authorized_signatory_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "authorized_signatory_seq_gen"
    )
    private Long id;

    private String customerCifNumber;

    private String signatoryFullName;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String telephoneNumber;

    private String mobileNumber;

    private String emailAddress;

    private String notificationContactName;

    private String signatoryCifNumber;

    private String notificationEmailAddress;

    @Lob
    @Column(name = "notification_signature_file")
    private byte[] notificationSignatureFile;

    private String notificationSignatureMimeType;

    private Boolean active;

    @ManyToOne
    private ApplicationSetting cifExists;

    @ManyToOne
    @JsonIgnore
    private Party party;

    private Boolean enabled ;

    private Boolean deleted ;

}
