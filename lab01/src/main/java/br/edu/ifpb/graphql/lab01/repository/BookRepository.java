package br.edu.ifpb.graphql.lab01.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.graphql.lab01.model.Book;

@Repository
public class BookRepository {
    
    private List<Book> books = new ArrayList<>();

    public void addBook( List<Book> books ) {
        this.books.addAll( books );
    }

    public Book getById( int id ) {
        return this.books.stream().filter( book -> book.getId() == id ).findFirst().orElse( null );
    }

}
