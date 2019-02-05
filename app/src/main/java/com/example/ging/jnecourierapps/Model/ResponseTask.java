package com.example.ging.jnecourierapps.Model;

import java.util.List;

public class ResponseTask {

    String error;
    List<GetTask> message;

    public String getError() {
        return error;
    }

    public List<GetTask> getMessage() {
        return message;
    }


}
