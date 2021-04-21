package com.soleir.soleirapi.resolver;

import com.soleir.soleirapi.model.SoleirDB.Appointment;
import com.soleir.soleirapi.repository.SoleirDB.AppointmentRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class AppointmentQueryResolver implements GraphQLQueryResolver {

    private AppointmentRepository repository;

    public AppointmentQueryResolver(AppointmentRepository repository) {
        this.repository = repository;
    }

    @PreAuthorize("isAuthenticated()")
    //this method would be for an admin role only suing Spring Security annotation : "hasRole('ADMIN')"
    public Iterable<Appointment> allAppointments(){
        return repository.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    // @PreAuthorize("isAnonymous()")
    public Appointment apptByApptIDUserID(Integer userID, String apptID) {
        return repository.findByUserIdAndApptID(userID, apptID);
    }








}
