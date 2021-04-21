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
public class StaffHosp {

    @Id
    @EqualsAndHashCode.Include
    private String staffID;
    private String firstname;
    private String surname;
    private String position;
    @OneToMany(mappedBy = "staffHosp")
    private Set<AppointmentHosp> appointmentHosp;
}

