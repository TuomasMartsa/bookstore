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
import fi.tuomas.bookstore.domain.User;
import fi.tuomas.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args)->{

			crepository.save(new Category("jännitys"));
			crepository.save(new Category("viihde"));
			crepository.save(new Category("romanssi"));
			
			repository.save(new Book("Matikka", "Ope", "IH456-4565", 20, 1984, crepository.findByname("jännitys").get(0)));
			repository.save(new Book("Äikkä", "Masa", "LK467-845", 10, 2020, crepository.findByname("viihde").get(0)));
			
			User user1 = new User("user", "$2a$10$7nK6ywnmFn0weNIKqu5yGeclT2Ov1R6hp21OS0DelFe46N45CainO", "USER");
			User user2 = new User("admin", "$2a$10$b/.SBOQcrMH0mqvfmdcLHO1ra76iCDaMfinkG4VZb1dTTMy2tmrFO", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
