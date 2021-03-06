package com.soleir.soleirapi.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SignInDetailsNotFoundException extends RuntimeException implements GraphQLError {

    private final int errorCode;

    public SignInDetailsNotFoundException(int errorCode, String errorMessage){
        super(errorMessage);

        this.errorCode=errorCode;
    }
    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> customAttributes = new LinkedHashMap<>();
        customAttributes.put("errorMessage",this.getMessage());

        return customAttributes;
    }
}
