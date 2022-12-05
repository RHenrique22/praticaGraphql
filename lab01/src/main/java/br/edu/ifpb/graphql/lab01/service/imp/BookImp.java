package br.edu.ifpb.graphql.lab01.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.graphql.lab01.model.Book;
import br.edu.ifpb.graphql.lab01.repository.BookRepository;
import br.edu.ifpb.graphql.lab01.service.BookService;

@Service
public class BookImp implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book getById( int id ) {
        return this.bookRepository.getById( id );
    }
    
}
