package br.edu.ifpb.graphql.lab01.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.graphql.lab01.model.Author;

@Repository
public class AuthorRepository {
    
    private List<Author> authors = new ArrayList<>();

    public void addAuthors( List<Author> authors ) {
        this.authors.addAll( authors );
    }

    public Author getById( int id ) {
        return this.authors.stream().filter( author -> author.getId() == id ).findFirst().orElse( null );
    }

}