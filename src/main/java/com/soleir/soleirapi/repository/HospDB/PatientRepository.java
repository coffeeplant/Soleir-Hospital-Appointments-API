package com.soleir.soleirapi.repository.HospDB;

import com.soleir.soleirapi.model.HospDB.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
