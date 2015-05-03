package com.adventureframework.core.exceptions;

public class InvalidAttributeConfigurationException extends RuntimeException {

    public InvalidAttributeConfigurationException() {
    }

    public InvalidAttributeConfigurationException(String message)
    {
        super(message);
    }
}
