package com.example.todoapi.controller.advice;

import com.example.todoapi.model.BadRequestError;
import com.example.todoapi.model.InvalidParam;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BadRequestErrorCreator {
        public static BadRequestError from(DataIntegrityViolationException ex) {
            var cause = ex.getMostSpecificCause();
            var invalidParam = new InvalidParam();
            invalidParam.setName(cause.getClass().getSimpleName());
            invalidParam.setReason(cause.getMessage());

            var invalidParamList = new ArrayList<InvalidParam>();
            invalidParamList.add(invalidParam);

            var error = new BadRequestError();
            error.setInvalidParams(invalidParamList);
            return error;
        }

}
