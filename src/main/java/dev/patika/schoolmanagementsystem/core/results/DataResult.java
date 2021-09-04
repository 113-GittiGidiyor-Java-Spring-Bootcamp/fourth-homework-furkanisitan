package dev.patika.schoolmanagementsystem.core.results;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DataResult<T> extends Result {

    private final T data;

    @Builder
    public DataResult(boolean success, String message, T data) {
        super(success, message);
        this.data = data;
    }
}
