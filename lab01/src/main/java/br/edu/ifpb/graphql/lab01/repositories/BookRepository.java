package br.edu.ifpb.graphql.lab01.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.graphql.lab01.models.Book;

@Repository
public class BookRepository {
    
    private List<Book> books = new ArrayList<>();

    public void addBooks( List<Book> books ) {
        this.books.addAll( books );
    }

    public void addBook( Book book ) {
        this.books.add( book );
    }

    public Book getById( int id ) {
        return this.books.stream().filter( book -> book.getId() == id ).findFirst().orElse( null );
    }

    public List<Book> findAll() {
        return this.books;
    }

    public List<Book> findByAuthor( String name ) {
        return this.books.stream().filter(
            book -> book.getAuthor().getFirstName().contains( name ) || book.getAuthor().getLastName().contains( name )
        ).toList();
    }

}
