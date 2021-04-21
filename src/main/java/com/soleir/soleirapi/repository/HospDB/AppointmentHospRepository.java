package com.soleir.soleirapi.repository.HospDB;


import com.soleir.soleirapi.model.HospDB.AppointmentHosp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentHospRepository extends JpaRepository<AppointmentHosp, String> {
}

