package com.sapanacciojob.bookManagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
public class BookController {

   BookService bookService=new BookService();
    @PostMapping("/add-book")
    public ResponseEntity addBook(@RequestBody Book book){
        try{
           Boolean added= bookService.addBook(book);

          return  new ResponseEntity("Book with Id: "+book.getBookId() +" is added to database successfully!!", HttpStatus.CREATED);

        }

       catch (BookAlreadyExistsException ex){
            return new ResponseEntity("Unable to add book as it already exists!!",HttpStatus.BAD_REQUEST);
       }

    }


    @GetMapping("/find-book")
    public ResponseEntity findBook( @RequestParam Integer id){
        try{
            Book book=bookService.getBook(id);
            return new ResponseEntity(book,HttpStatus.OK);
        }
        catch (BookNotFoundException ex){
            return new ResponseEntity("Book with id: "+id+"is not found",HttpStatus.valueOf(404));
        }
        catch (Exception ex){
            return new ResponseEntity("something wrong",HttpStatus.valueOf(500));
        }
    }


    @PutMapping("/update-book/{id}")
    public String updateBook(@PathVariable Integer id , @RequestParam(required = false) String title , @RequestParam(required = false) String author , @RequestParam(required = false) Integer pages){
        try{
            String response= bookService.updateBook(id,title,author,pages);
            return response;
        }
        catch (Exception ex){
            return "Exception Occurs!!";
        }
    }


    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity deleteBook(@PathVariable Integer id){
        try{
            bookService.deleteBook(id);
            return  new ResponseEntity("Book with id:"+id+" is removed successfully!!",HttpStatus.OK);
        }
        catch (BookNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


}
