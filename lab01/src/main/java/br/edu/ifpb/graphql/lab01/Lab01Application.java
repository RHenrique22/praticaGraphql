package br.edu.ifpb.graphql.lab01;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifpb.graphql.lab01.model.Author;
import br.edu.ifpb.graphql.lab01.model.Book;
import br.edu.ifpb.graphql.lab01.repository.AuthorRepository;
import br.edu.ifpb.graphql.lab01.repository.BookRepository;

@SpringBootApplication
public class Lab01Application implements CommandLineRunner {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;

	public static void main( String[] args ) {
		SpringApplication.run( Lab01Application.class, args );
	}

	@Override
	public void run(String... args) throws Exception {

		Author a1 = new Author( 1, "Joanne", "Rowling" );
		Author a2 = new Author( 2, "Herman", "Melville" );
		Author a3 = new Author( 3, "Anne", "Rice" );

		List<Author> authors = new ArrayList<>();

		authors.add( a1 );
		authors.add( a2 );
		authors.add( a3 );

		this.authorRepository.addAuthors( authors );

		Book   b1 = new Book( 1, "Harry Potter and the Philosopher's Stone", 223, a1 );
		Book   b2 = new Book( 2, "Moby Dick", 635, a2 );
		Book   b3 = new Book( 3, "Interview with the vampire", 371, a3 );

		List<Book> books = new ArrayList<>();

		books.add( b1 );
		books.add( b2 );
		books.add( b3 );

		this.bookRepository.addBook( books );
	}

}
