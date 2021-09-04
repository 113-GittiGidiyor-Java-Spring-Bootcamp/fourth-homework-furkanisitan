package dev.patika.schoolmanagementsystem.core.results;

import lombok.Getter;

@Getter
public class Result {

    private final boolean success;
    private final String message;

    Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static Result ok() {
        return new Result(true, null);
    }

    public static Result ok(String message) {
        return new Result(true, message);
    }

    public static Result fail() {
        return new Result(false, null);
    }

    public static Result fail(String message) {
        return new Result(false, message);
    }
}
