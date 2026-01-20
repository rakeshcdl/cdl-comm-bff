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
@Table(name = "account_type")
public class AccountType implements Serializable {

    @Id
    @SequenceGenerator(
            name = "account_type_id_seq_gen",
            sequenceName = "account_type_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_type_seq_gen"
    )
    private Long id;

    private String labelCode;

    private String typeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    @OneToMany(mappedBy = "accountType")
   @JsonIgnore
    private Set<AccountTypeCategory> childBankAccountTypes = new HashSet<>();

   /* @OneToMany(mappedBy = "accountType")
    @JsonIgnore
    private Set<EscrowAccount> escrowAccounts = new HashSet<>();
*/
    private Boolean enabled ;

    private Boolean deleted ;
}
