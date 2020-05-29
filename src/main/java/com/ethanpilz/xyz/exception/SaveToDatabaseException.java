package com.ethanpilz.xyz.exception;

public class SaveToDatabaseException extends Exception{

        public SaveToDatabaseException() {}

        // Constructor that accepts a message
        public SaveToDatabaseException(String message)
        {
            super(message);
        }
    }

