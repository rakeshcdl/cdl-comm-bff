package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "account_type_category")
public class AccountTypeCategory implements Serializable {

    @Id
    @SequenceGenerator(
            name = "account_type_category_id_seq_gen",
            sequenceName = "account_type_category_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_type_category_seq_gen"
    )
    private Long id;

    private String labelCode;

    private String typeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    @ManyToOne
    @JsonIgnore
    private AccountType accountType;

    @OneToMany(mappedBy = "accountTypeCategory")
   @JsonIgnore
    private Set<EscrowAccount> escrowAccountSet = new HashSet<>();

    private Boolean enabled ;

    private Boolean deleted ;
}
