package hhplus.ecommoerce.exception;

import java.util.List;

public record ErrorResponse(

    String code,
    String message,

    List<String> error
) {

    public ErrorResponse(String code, String message) {
        this(code, message, null);
    }

}
