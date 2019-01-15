package com.example.ging.jnecourierapps.Model;

import java.util.List;

public class Login {

    Profile success_login;
    String token;
    String error;

    public Profile getSuccess_login(){
        return success_login;
    }

    public String getToken() {
        return token;
    }

    public String getError() {
        return error;
    }
}
