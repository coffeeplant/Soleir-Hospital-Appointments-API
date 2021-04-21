package com.soleir.soleirapi.resolver;

import com.soleir.soleirapi.model.SoleirDB.Appointment;
import com.soleir.soleirapi.model.SoleirDB.AppointmentNoteInput;
import com.soleir.soleirapi.repository.SoleirDB.AppointmentRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class AppointmentMutableResolver  implements GraphQLMutationResolver {
    AppointmentRepository appointmentRepository;

    public AppointmentMutableResolver(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    @Transactional
    //@PreAuthorize("isAnonymous()")
    @PreAuthorize("isAuthenticated()")
    public Appointment editNote(AppointmentNoteInput input){
        Appointment appointment = appointmentRepository.findByUserIdAndApptID(input.getUserID(), input.getApptID());
        appointment.setNote(input.getNote());
        return appointment;
    }
}