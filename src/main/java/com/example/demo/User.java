package com.example.demo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class User {

    private String firstName;
    private String lastName;
    private Integer age;
    private String place;
    private Integer count;
}