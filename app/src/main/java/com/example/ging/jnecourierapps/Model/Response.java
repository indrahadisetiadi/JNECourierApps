package com.example.ging.jnecourierapps.Model;

import java.util.List;

public class Response {

    String error;
    List<GetTask> message;

    public String getError() {
        return error;
    }

    public List<GetTask> getMessage() {
        return message;
    }


}
