package com.example.demo.exception;

public class DataRequestException extends RuntimeException{
    public DataRequestException(String message){
        super(message);
    }

}