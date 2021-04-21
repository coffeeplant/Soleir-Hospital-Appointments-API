package com.soleir.soleirapi.resolver;

import com.soleir.soleirapi.model.SoleirDB.SoleirUser;
import com.soleir.soleirapi.model.SoleirDB.Appointment;
import com.soleir.soleirapi.repository.SoleirDB.SoleirUserRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.NoSuchElementException;

@Component
public class SoleirUserQueryResolver implements GraphQLQueryResolver {

    private SoleirUserRepository repository;

    SoleirUserQueryResolver(SoleirUserRepository repository){
        this.repository=repository;
    }

    @PreAuthorize("isAuthenticated()")
    //@PreAuthorize("isAnonymous()")
    public Iterable<SoleirUser> allUsers(){
        return repository.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    //@PreAuthorize("isAnonymous()")
    public SoleirUser userByID(Integer id, DataFetchingEnvironment environment) {
        Specification<SoleirUser> spec = byId(id);
        DataFetchingFieldSelectionSet s = environment.getSelectionSet();
        if (s.contains("appointment"))
            spec = spec.and(fetchAppointments());
        return repository.findOne(spec).orElseThrow(NoSuchElementException::new);
    }
    //this implementation was before JPA and correct linking of entities. Appointments query resolver doesn't need this
    private  Specification<SoleirUser> fetchAppointments(){
        return (Specification<SoleirUser>) (root, query, builder) ->{
            Fetch<SoleirUser, Appointment> f = root.fetch("appointment", JoinType.LEFT);
            Join<SoleirUser, Appointment> join = (Join<SoleirUser, Appointment>) f;
            return join.getOn();
        };
    }

    private Specification<SoleirUser> byId(Integer id) {
        return (Specification<SoleirUser>) (root, query, builder) -> builder.equal(root.get("userID"), id);
    }
}
