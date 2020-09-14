package fi.tuomas.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.tuomas.bookstore.domain.Book;
import fi.tuomas.bookstore.domain.BookRepository;
import fi.tuomas.bookstore.domain.Category;
import fi.tuomas.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository) {
		return (args)->{

			crepository.save(new Category("jännitys"));
			crepository.save(new Category("viihde"));
			
			repository.save(new Book("Matikka", "Kirjoittaja", "ij4456-5", 20, 2020));
			repository.save(new Book("Äikkä", "Kirjoittaja", "ij4456-5", 20, 2020));
			
			
			
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
