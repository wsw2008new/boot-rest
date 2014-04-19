package com.github.robi42.boot.rest.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class BootRestException extends WebApplicationException {

    public BootRestException(final Response.Status status, final String message) {
        super(Response.status(status)
                .entity(errorResponseBodyWith(message))
                .build());
    }

    public BootRestException(final int statusCode, final String message) {
        super(Response.status(statusCode)
                .entity(errorResponseBodyWith(message))
                .build());
    }

    private static ErrorResponseBody errorResponseBodyWith(final String message) {
        return ErrorResponseBody.builder().errorMessage(message).build();
    }
}