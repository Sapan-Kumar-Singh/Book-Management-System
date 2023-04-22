package com.sapanacciojob.bookManagement;

import java.util.Objects;
import java.util.Optional;

public class BookService {
    BookRepository bookRepository =new BookRepository();
    public boolean addBook(Book book) throws BookAlreadyExistsException{
        // book with id already exists
        Optional<Book>bookOpt=bookRepository.getById(book.getBookId());

        if(bookOpt.isPresent()){
            throw new BookAlreadyExistsException(book.getBookId());
        }
       return  bookRepository.add(book);

    }

    public Book getBook(Integer id) throws BookNotFoundException{
        Optional<Book>bookOpt=bookRepository.getById(id);
        if(bookOpt.isEmpty()){
            throw new BookNotFoundException(id);
        }
          return bookOpt.get();
    }

    public String updateBook(Integer id, String title, String author, Integer pages) {

        try{
            Book book=getBook(id);
            if(Objects.nonNull(title)){
                book.setTitle(title);
            }
            if(Objects.nonNull(author)){
                book.setAuthor(author);
            }
            if(Objects.nonNull(pages)){
                book.setPages(pages);
            }
            bookRepository.add(book);
            return "Book updated!!";
        }
        catch(BookNotFoundException ex){
           Book book=new Book(id,title,author,pages);
           bookRepository.add(book);
           return "Book Added";
        }

    }

    public Boolean deleteBook(Integer id) throws BookNotFoundException{
        Optional<Book>bookOpt=bookRepository.getById(id);
        if(bookOpt.isEmpty()){
            throw new BookNotFoundException(id);
        }
        bookRepository.removeById(id);
        return true;
    }
}
