package guru.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

   

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
    	Publisher publisher = new Publisher();
    	publisher.setName("Editura Culturala");
    	publisher.setAddress("Bucuresti");
    	publisherRepository.save(publisher);
    	
        Author eminescu = new Author("Mihai", "Eminescu");
        Book poetryBook = new Book("Poezii", "1234", publisher);
        eminescu.getBooks().add(poetryBook);
        poetryBook.getAuthors().add(eminescu);


        authorRepository.save(eminescu);

        bookRepository.save(poetryBook);



        Author creanga = new Author("Ion", "Creanga");
        Book amintiri = new Book("Amintiri din copilarie", "2345", publisher);
        creanga.getBooks().add(amintiri);
        //amintiri.getAuthors().add(creanga);


        authorRepository.save(creanga);
        bookRepository.save(amintiri);



    }
}
