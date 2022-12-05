package br.edu.ifpb.graphql.lab01.service;

import br.edu.ifpb.graphql.lab01.model.Book;

public interface BookService {
    Book getById( int id );
}
