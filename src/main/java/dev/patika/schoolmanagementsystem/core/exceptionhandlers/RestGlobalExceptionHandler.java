package dev.patika.schoolmanagementsystem.core.exceptionhandlers;

import dev.patika.schoolmanagementsystem.core.constants.ResponseMessages;
import dev.patika.schoolmanagementsystem.core.exceptions.NotAllowedFilterCriteriaException;
import dev.patika.schoolmanagementsystem.core.exceptions.CustomValidationException;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.core.results.Result;
import dev.patika.schoolmanagementsystem.core.specifications.exceptions.InvalidFilterException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestGlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotExistsException.class)
    @ResponseBody
    Result handleEntityNotExistsException(EntityNotExistsException e) {
        return Result.fail(ResponseMessages.ERR_NOT_FOUND, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NotAllowedFilterCriteriaException.class, InvalidFilterException.class})
    @ResponseBody
    Result handleInvalidFilterException(Exception e) {
        return Result.fail(ResponseMessages.ERR_INVALID_FILTER_CRITERIA, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomValidationException.class)
    @ResponseBody
    Result handleCustomValidationException(CustomValidationException e) {
        return Result.fail(ResponseMessages.ERR_VALIDATION, e.getMessage());
    }

}
