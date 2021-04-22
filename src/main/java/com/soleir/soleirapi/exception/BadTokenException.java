package com.soleir.soleirapi.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class BadTokenException extends RuntimeException implements GraphQLError{

   // private static final long serialVersionUID = 158136221282852553L;

    public BadTokenException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }
}