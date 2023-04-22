package com.sapanacciojob.bookManagement;

 class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(int bookId) {
        super("Book for id : "+bookId +" is already Exists in database");
    }
}
