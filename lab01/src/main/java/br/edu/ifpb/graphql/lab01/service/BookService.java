package br.edu.ifpb.graphql.lab01.service;

import java.util.List;

import br.edu.ifpb.graphql.lab01.model.Book;

public interface BookService {
    Book findById( int id );
    List<Book> findAll();
}
