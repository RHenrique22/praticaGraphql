package br.edu.ifpb.graphql.lab01.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.graphql.lab01.models.Book;
import br.edu.ifpb.graphql.lab01.repositories.BookRepository;
import br.edu.ifpb.graphql.lab01.services.BookService;

@Service
public class BookImp implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book findById( int id ) {
        return this.bookRepository.getById( id );
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByAuthor( String name ) {
        return this.bookRepository.findByAuthor( name );
    }
    
}
