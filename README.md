# Prática Graphql com Spring Boot
## Passo 1

Inicialmente é necessário criar um aplicativo Spring Boot, utilizando o Spring Initializr trazendo os seguintes Starters

```
Spring Web
Spring Boot DevTools
Spring for GraphQL
Lombok
```
---
## Passo 2

Após a criação do projeto, crie um diretório chamado `graphql` no path: `src/main/resources`. Logo após adicione um arquivo `schema.graphqls` com `src/main/resources/graphql` o seguinte conteúdo:

``` graphqls
type Query {
    bookById( id: ID ): Book
    getBooks:  [ Book ]
    getAuthors: [ Author ]
}

type Book {
    id: ID
    name: String
    pageCount: Int
    author: Author
}

type Author {
    id: ID
    firstName: String
    lastName: String
}
```

A Linguagem Específica de Domínio (mostrada acima) é usada para descrever um esquema e é chamada de Linguagem de Definição de Esquema ou SDL. São consultas que mais a frente iremos ver mais detalhadamente

---
## Passo 3

Em seguida, crie um diretório chamado `models` na sua aplicação onde criaremos duas classes: `Book.java` e `Auhtor.java`, com o seguinte código:

``` java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private int     id;
    private String  name;
    private int     pageCount;
    private Author  author;
    
}
```
``` java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private int     id;
    private String  firstName;
    private String  lastName;

}
```
---
## Passo 4

Em seguida, crie um diretório chamado `repositories` onde será armazenado os seus objetos, para simplificar usaremos listas, mas pode ser usado banco de dados para o mesmo processo. Criando os arquivos para armazenamento dos dados `BookRepository.java` e `AuthorRepository.java`, com o seguinte código:

``` java
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.graphql.lab01.models.Book;

@Repository
public class BookRepository {
    
    private List<Book> books = new ArrayList<>();

    public void addBook( List<Book> books ) {
        this.books.addAll( books );
    }

    public Book getById( int id ) {
        return this.books.stream().filter( book -> book.getId() == id ).findFirst().orElse( null );
    }

    public List<Book> findAll() {
        return this.books;
    }

    public List<Book> findByAuthor( String name ) {
        return this.books.stream().filter(
            book -> book.getAuthor().getFirstName().contains( name ) || book.getAuthor().getLastName().contains( name )
        ).toList();
    }

}
```
``` java
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

    public Author getById( int id ) {
        return this.authors.stream().filter( author -> author.getId() == id ).findFirst().orElse( null );
    }

    public List<Author> findAll() {
        return this.authors;
    }

}
```
---
## Passo 5

Em seguida criaremos os serviços da aplicação, crie um diretório chamado `services` e logo após os seguintes arquivos `BookService.java` e `AuthorService.java` que serão interfaces para implementação futura, com o seguinte código:
``` java
import java.util.List;

import br.edu.ifpb.graphql.lab01.models.Book;

public interface BookService {
    Book findById( int id );
    List<Book> findAll();
    List<Book> findByAuthor( String name );
}
```
``` java
import java.util.List;

import br.edu.ifpb.graphql.lab01.models.Author;

public interface AuthorService {
    Author findById( int id );
    List<Author> findAll();
}
```
---
## Passo 6

Criaremos as classes concretas que implementarão as interfaces criadas anteriormente no `Passo 5`, no path `services` crie o diretório `imp` e dentro deste diretório os seguintes arquivos: `BookImp.java` e `AuthorImp.java`, com o seguinte código:
``` java
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
```
``` java
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.graphql.lab01.models.Author;
import br.edu.ifpb.graphql.lab01.repositories.AuthorRepository;
import br.edu.ifpb.graphql.lab01.services.AuthorService;

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
```
---
## Passo 7

Agora criaremos o diretório `controllers` que ficará o controller da aplicação onde será feito as aquisições e o controle das querys do GraphQL, dentro do diretório `controllers`  crie o arquivo  `BookController.java`, com o seguinte código:
``` java
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
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
```

O mapeamento dos métodos necessita que possuam a mesma nomenclatura que as querys criadas no `Passo 2` para que as mesmas sejam interpretadas e sejam usadas como o esperado

## Passo 8

Antes de finalizarmos a aplicação podemos ativar as seguintes configurações no `application.properties`

``` properties
spring.graphql.graphiql.enabled = true
spring.graphql.graphiql.path    = /graphiql
```

Com essas configurações estamos habilitando o recurso de playground de testes de query do GraphQL para o navegador na porta `http://localhost:8080/graphiql` para brincarmos com as querys criadas e testarmos a mesma, mas caso queira podemos usar um programa de requisições para testar via POSTMAN ou ThunderClient com a coleção no seguinte link: [Coleção](https://raw.githubusercontent.com/RHenrique22/praticaGraphql/master/lab01/request/thunder-collection_ProgDist.json) e logo após clique CTRL + S, salve o arquivo e importe num programa de requisições de sua preferência.