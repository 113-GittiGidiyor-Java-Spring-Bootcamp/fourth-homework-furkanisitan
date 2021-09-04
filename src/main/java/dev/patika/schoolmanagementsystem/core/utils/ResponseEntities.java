package dev.patika.schoolmanagementsystem.core.utils;

import dev.patika.schoolmanagementsystem.core.constants.ResponseMessages;
import dev.patika.schoolmanagementsystem.core.results.DataResult;
import dev.patika.schoolmanagementsystem.core.results.Result;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntities {

    @SafeVarargs
    public static ResponseEntity<Result> notFoundResult(String name, Pair<String, Object>... parameters) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Result.fail(ResponseMessages.NOT_FOUND, ApiErrors.notFound(name, parameters)));
    }

    @SafeVarargs
    public static <T> ResponseEntity<DataResult<T>> notFoundDataResult(String name, Pair<String, Object>... parameters) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(DataResult.<T>builder().message(ResponseMessages.NOT_FOUND).errors(ApiErrors.notFound(name, parameters)).build());
    }
}
