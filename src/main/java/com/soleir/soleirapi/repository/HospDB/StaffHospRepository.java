package com.soleir.soleirapi.repository.HospDB;

import com.soleir.soleirapi.model.HospDB.StaffHosp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffHospRepository extends JpaRepository<StaffHosp, String> {
}
