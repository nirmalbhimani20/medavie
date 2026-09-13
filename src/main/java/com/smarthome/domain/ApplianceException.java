package com.smarthome.domain;

/**
 * Thrown when a device cannot be controlled.
 * Unchecked so adapters do not force every caller to add throws.
 */
public class ApplianceException extends RuntimeException {

    public ApplianceException(String message) {
        super(message);
    }

    public ApplianceException(String message, Throwable cause) {
        super(message, cause);
    }
}
