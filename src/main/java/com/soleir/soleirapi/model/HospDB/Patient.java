package com.soleir.soleirapi.model.HospDB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Patient {

    @Id
    private Integer hospID;
    private String name;
    private String email;
    private String soleirID;
    @OneToMany(mappedBy = "patient")
    private Set<AppointmentHosp> appointmentHosp;


}