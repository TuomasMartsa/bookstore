package fi.tuomas.bookstore;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.tuomas.bookstore.domain.Book;
import fi.tuomas.bookstore.domain.BookRepository;
import fi.tuomas.bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
	private BookRepository brepository;
	
	@Test
	public void findByName() {
		List<Book> books = brepository.findByname("Matikka");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Ope");
	}
	
	@Test
	public void createNewBook() {
		Book book = new Book("A", "B", "C", 1, 1984, new Category("ASD"));
		brepository.save(book);
		
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		brepository.deleteAll();
		Iterable<Book> books = brepository.findAll();
		assertThat(books).hasSize(0);
	}

}
