package com.example.springstudy.exception;

import java.io.Serial;

public class StudyException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public StudyException(String message) {
        super(message);
    }
}
