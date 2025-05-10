package com.example.Account.Json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {
    public String jsonConverter(String data){

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert string to JSON format

            return objectMapper.writeValueAsString(data);

        } catch (Exception e) {
           return "something went wrong";
        }
    }
}
