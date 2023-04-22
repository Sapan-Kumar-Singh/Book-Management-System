package com.sapanacciojob.bookManagement;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Integer id) {
        super("Book for id : "+ id+" is not found ");
    }
}
