package com.soleir.soleirapi.model.SoleirDB;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Appointment {

    @Id
    @EqualsAndHashCode.Include
    private String apptID;
    private Date apptdatetime;
    private String clinic;
    private String note;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "fkuserID")
    private SoleirUser soleiruser;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "fkstaffID")
    private Staff staff;


}
