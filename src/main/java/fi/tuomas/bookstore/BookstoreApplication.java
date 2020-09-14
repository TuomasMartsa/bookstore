package fi.tuomas.bookstore;

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

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository catrepository) {
		return (args)->{
			repository.save(new Book("Matikka", "Kirjoittaja", "ij4456-5", 20, 2020));
			repository.save(new Book("Äikkä", "Kirjoittaja", "ij4456-5", 20, 2020));
			
			catrepository.save(new Category("viihde"));
			catrepository.save(new Category("jännitys"));
		};
	}

}
