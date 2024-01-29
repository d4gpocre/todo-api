package com.example.todoapi.service.sample;

import org.springframework.stereotype.Service;

import com.example.todoapi.repository.sample.SampleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository repository;
    
    public SampleEntity find(){
        var record = repository.select();
        return new SampleEntity(record.getContent());
    }
    
}
