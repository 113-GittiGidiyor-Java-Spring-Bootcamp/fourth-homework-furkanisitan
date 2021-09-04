package dev.patika.schoolmanagementsystem.core.results;

import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class DataResult<T> extends Result {

    private final T data;

    @Builder
    public DataResult(T data, boolean success, String message, String... errors) {
        super(success, message, Arrays.asList(errors));
        this.data = data;
    }
}
