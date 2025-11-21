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
@Table(name = "account_purpose")
public class AccountPurpose implements Serializable {
    @Id
    @SequenceGenerator(
            name = "account_purpose_id_seq_gen",
            sequenceName = "account_purpose_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_purpose_seq_gen"
    )
    private Long id;

    private String UUID;

    private String accountPurposeCode;

    private String accountPurposeName;

    private Boolean active;

    @ManyToOne
    private ApplicationSetting criticality;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    @OneToMany(mappedBy = "accountPurpose")
    @JsonIgnore
    private Set<EscrowAccount> escrowAccounts = new HashSet<>();


    private Boolean enabled ;

    private Boolean deleted ;
}
