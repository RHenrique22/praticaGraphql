package br.edu.ifpb.graphql.lab01.service;

import java.util.List;

import br.edu.ifpb.graphql.lab01.model.Author;

public interface AuthorService {
    Author findById( int id );
    List<Author> findAll();
}
