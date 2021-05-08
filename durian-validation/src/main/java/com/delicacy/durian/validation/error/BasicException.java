package com.delicacy.durian.validation.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Setter
@Getter
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class BasicException extends RuntimeException {
    private Integer code;

    public BasicException() {
    }

    public BasicException(int code, String message) {
        super(message);
        this.code = code;
    }
}