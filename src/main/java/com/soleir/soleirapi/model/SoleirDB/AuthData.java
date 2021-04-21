package com.soleir.soleirapi.model.SoleirDB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;

@Data
@RequiredArgsConstructor
public class AuthData {

    private String soleirID;
    private String email;

}

