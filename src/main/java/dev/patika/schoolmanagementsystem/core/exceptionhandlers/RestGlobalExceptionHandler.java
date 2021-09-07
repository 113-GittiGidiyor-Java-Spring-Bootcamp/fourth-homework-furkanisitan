package dev.patika.schoolmanagementsystem.core.exceptionhandlers;

import dev.patika.schoolmanagementsystem.core.constants.ResponseMessages;
import dev.patika.schoolmanagementsystem.core.criteria.NotAllowedFilterCriteriaException;
import dev.patika.schoolmanagementsystem.core.results.Result;
import dev.patika.schoolmanagementsystem.core.specifications.exceptions.InvalidFilterException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestGlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NotAllowedFilterCriteriaException.class, InvalidFilterException.class})
    @ResponseBody
    Result handleInvalidEntityTypeException(Exception e) {
        return Result.fail(ResponseMessages.INVALID_FILTER_CRITERIA, e.getMessage());
    }
}
