package com.soleir.soleirapi.repository.SoleirDB;

import com.soleir.soleirapi.model.SoleirDB.Appointment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer>,
        JpaSpecificationExecutor<Appointment> {

    @Query("FROM Appointment a WHERE a.apptID = :apptID AND a.soleiruser.userID = :userID")
    Appointment findByUserIdAndApptID(Integer userID, String apptID);
}

