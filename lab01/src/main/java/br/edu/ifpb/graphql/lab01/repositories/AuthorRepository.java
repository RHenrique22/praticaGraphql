package br.edu.ifpb.graphql.lab01.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.graphql.lab01.models.Author;

@Repository
public class AuthorRepository {
    
    private List<Author> authors = new ArrayList<>();

    public void addAuthors( List<Author> authors ) {
        this.authors.addAll( authors );
    }

    public void addAuthor( Author author ) {
        this.authors.add( author );
    }

    public Author getById( int id ) {
        return this.authors.stream().filter( author -> author.getId() == id ).findFirst().orElse( null );
    }

    public List<Author> findAll() {
        return this.authors;
    }

}
