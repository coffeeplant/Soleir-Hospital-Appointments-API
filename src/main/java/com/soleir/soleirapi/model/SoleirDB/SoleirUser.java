package com.soleir.soleirapi.model.SoleirDB;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Soleiruser")
public class SoleirUser {

    @Id
    @Column(name = "userid")
    @EqualsAndHashCode.Include
    private Integer userID;
    @EqualsAndHashCode.Include
    private String soleirID;
    @EqualsAndHashCode.Include
    private String email;
    @OneToMany (mappedBy = "soleiruser")
    private Set<Appointment> appointment;

}

