package br.edu.ifpb.graphql.lab01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import br.edu.ifpb.graphql.lab01.model.Author;
import br.edu.ifpb.graphql.lab01.model.Book;
import br.edu.ifpb.graphql.lab01.service.AuthorService;
import br.edu.ifpb.graphql.lab01.service.BookService;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @QueryMapping
    public Book bookById( @Argument int id ) {
        return this.bookService.getById( id );
    }

    @SchemaMapping
    public Author author( Book book ) {
        return this.authorService.getById( book.getAuthor().getId() );
    }

}
