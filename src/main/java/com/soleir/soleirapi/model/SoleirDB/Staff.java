package com.soleir.soleirapi.model.SoleirDB;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Staff {

    @Id
    @EqualsAndHashCode.Include
    private String staffID;
    private String firstname;
    private String surname;
    private String position;
    @OneToMany(mappedBy = "staff")
    private Set<Appointment> appointment;
}

