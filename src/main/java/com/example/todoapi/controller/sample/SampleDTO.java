package com.example.todoapi.controller.sample;

import java.time.LocalDateTime;

import lombok.Value;

@Value
public class SampleDTO {
    
    private String content;
    private LocalDateTime timestamp;

}
