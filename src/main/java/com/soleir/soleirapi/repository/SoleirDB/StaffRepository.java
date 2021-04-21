package com.soleir.soleirapi.repository.SoleirDB;

import com.soleir.soleirapi.model.SoleirDB.Staff;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Integer>,
        JpaSpecificationExecutor<Staff> {

}
