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

		Author a1 = new Author( 
			1, 
			"Machado",
			"de Assis"
		);

		Author a2 = new Author( 
			2, 
			"Euclides", 
			"da Cunha"
		);

		Author a3 = new Author( 
			3,
			"Carlos Drummond", 
			"de Andrade"
		);



		List<Author> authors = new ArrayList<>();

		authors.add( a1 );
		authors.add( a2 );
		authors.add( a3 );

		this.authorRepository.addAuthors( authors );

		Book   	b1 = new Book( 1, "Dom Casmurro", 210, a1 );
		Book   	b2 = new Book( 2, "Memórias Póstumas de Brás Cubas", 320, a1 );
		Book   	b3 = new Book( 3, "Quincas Borba", 270, a1 );

		Book	b4 = new Book(4, "Os Sertões", 496 , a2);
		Book	b5 = new Book(5, "Canudos (diario de uma expedição)", 184, a2);
		Book	b6 = new Book(6, "Amazônia", 261 , a2);

		Book	b7 = new Book(7, "Uma pedra no meio do caminho", 194, a3);
		Book	b8 = new Book(8, "Vasto mundo", 168 , a3);
		Book	b9 = new Book(9, "Brejo das almas", 80 , a3);

		List<Book> books = new ArrayList<>();

		books.add( b1 );
		books.add( b2 );
		books.add( b3 );
		books.add( b4 );
		books.add( b5 );
		books.add( b6 );
		books.add( b7 );
		books.add( b8 );
		books.add( b9 );

		this.bookRepository.addBook( books );
	}

}
