package br.edu.ifpb.graphql.lab01.services;

import java.util.List;

import br.edu.ifpb.graphql.lab01.models.Book;

public interface BookService {
    Book findById( int id );
    List<Book> findAll();
    List<Book> findByAuthor( String name );
}
