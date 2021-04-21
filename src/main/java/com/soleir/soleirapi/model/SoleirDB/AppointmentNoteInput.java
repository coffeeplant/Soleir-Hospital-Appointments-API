package com.soleir.soleirapi.model.SoleirDB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentNoteInput {

    private String apptID;
    private String note;
    private Integer userID;

}
