package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author eminescu = new Author("Mihai", "Eminescu");
        Book poetryBook = new Book("Poezii", "1234", "Editura Culturala");
        eminescu.getBooks().add(poetryBook);
        poetryBook.getAuthors().add(eminescu);


        authorRepository.save(eminescu);

        bookRepository.save(poetryBook);



        Author creanga = new Author("Ion", "Creanga");
        Book amintiri = new Book("Amintiri din copilarie", "2345", "Cultura Romana");
        creanga.getBooks().add(amintiri);
        //amintiri.getAuthors().add(creanga);


        authorRepository.save(creanga);
        bookRepository.save(amintiri);



    }
}
