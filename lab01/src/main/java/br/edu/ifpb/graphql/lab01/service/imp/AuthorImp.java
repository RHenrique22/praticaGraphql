package br.edu.ifpb.graphql.lab01.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.graphql.lab01.model.Author;
import br.edu.ifpb.graphql.lab01.repository.AuthorRepository;
import br.edu.ifpb.graphql.lab01.service.AuthorService;

@Service
public class AuthorImp implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author findById( int id ) {
        return this.authorRepository.getById( id );
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }
    
}
