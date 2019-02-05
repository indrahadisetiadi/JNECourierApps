package com.example.ging.jnecourierapps.Model;

import java.util.List;

public class ResponseHistory {
    String error;
    List<GetHistory> message;

    public String getError() {
        return error;
    }

    public List<GetHistory> getMessage() {
        return message;
    }

}
