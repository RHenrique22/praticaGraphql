package br.edu.ifpb.graphql.lab01.service;

import br.edu.ifpb.graphql.lab01.model.Author;

public interface AuthorService {
    Author getById( int id );
}
