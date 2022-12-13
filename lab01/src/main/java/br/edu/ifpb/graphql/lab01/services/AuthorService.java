package br.edu.ifpb.graphql.lab01.services;

import java.util.List;

import br.edu.ifpb.graphql.lab01.models.Author;

public interface AuthorService {
    Author findById( int id );
    List<Author> findAll();
}
