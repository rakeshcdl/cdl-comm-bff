package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Jacksonized
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "beneficiary")
public class Beneficiary implements Serializable {

    @Id
    @SequenceGenerator(
            name = "beneficiary_id_seq_gen",
            sequenceName = "beneficiary_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "beneficiary_seq_gen"
    )
    private Long id;

    private String UUID;

    private String beneficiaryFullName;

    private String beneficiaryAddressLine1;

    private String telephoneNumber;

    private String mobileNumber;

    private String beneficiaryAccountNumber;

    private String bankIfscCode;

    private String beneficiaryBankName;

    private String bankRoutingCode;

    private String additionalRemarks;

    private Boolean active;

    @ManyToOne
    private ApplicationSetting accountType;

    @ManyToOne
    private ApplicationSetting transferType;

    @ManyToOne
    private ApplicationSetting role;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    @OneToMany(mappedBy = "beneficiary")
    @JsonIgnore
    private Set<PartyDocument> documents = new HashSet<>();

    @OneToMany(mappedBy = "beneficiary")
    @JsonIgnore
    private Set<BulkPaymentUpload> bulkUploads = new HashSet<>();

    private Boolean enabled ;

    private Boolean deleted ;
}
