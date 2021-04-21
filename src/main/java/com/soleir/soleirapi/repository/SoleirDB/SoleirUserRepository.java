package com.soleir.soleirapi.repository.SoleirDB;

import com.soleir.soleirapi.model.SoleirDB.SoleirUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SoleirUserRepository extends CrudRepository<SoleirUser, Integer>,
        JpaSpecificationExecutor<SoleirUser> {

    @Query("FROM SoleirUser s WHERE s.soleirID = :soleirID AND s.email = :email")
    SoleirUser findByAuthData(String soleirID, String email);

    //used by authentication
    Optional<SoleirUser> findByEmail(String email);
}

