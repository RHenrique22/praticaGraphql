package br.edu.ifpb.graphql.lab01.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import br.edu.ifpb.graphql.lab01.models.Author;
import br.edu.ifpb.graphql.lab01.models.Book;
import br.edu.ifpb.graphql.lab01.services.AuthorService;
import br.edu.ifpb.graphql.lab01.services.BookService;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @QueryMapping
    public Book bookById( @Argument int id ) {
        return this.bookService.findById( id );
    }

    @SchemaMapping
    public Author author( Book book ) {
        return this.authorService.findById( book.getAuthor().getId() );
    }

    @QueryMapping
    public List<Book> getBooks() {
        return this.bookService.findAll();
    }

    @QueryMapping
    public List<Author> getAuthors() {
        return this.authorService.findAll();
    }

    @QueryMapping
    public List<Book> booksByAuthor( @Argument String name ) {
        return this.bookService.findByAuthor( name );
    }

}
