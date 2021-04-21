package com.soleir.soleirapi.model.HospDB;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AppointmentHosp {

    @Id
    @EqualsAndHashCode.Include
    private String apptID;
    private Date apptdatetime;
    private String clinic;
    private String note;
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;
    @ManyToOne(fetch = FetchType.LAZY)
    private StaffHosp staffHosp;

}