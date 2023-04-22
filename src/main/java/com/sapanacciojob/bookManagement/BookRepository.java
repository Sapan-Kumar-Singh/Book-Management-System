package com.sapanacciojob.bookManagement;

import java.util.HashMap;
import java.util.Optional;

public class BookRepository {
    HashMap<Integer,Book> data=new HashMap<>();

    public boolean add(Book book) {
        data.put(book.getBookId(),book);
        return true;
    }

    public Optional<Book> getById(Integer id) {
           if(data.containsKey(id)){
               return Optional.of(data.get(id));
           }

           return Optional.empty();
    }


    public void removeById(Integer id) {
        data.remove(id);

    }
}
