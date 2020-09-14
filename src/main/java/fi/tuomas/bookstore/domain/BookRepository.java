package fi.tuomas.bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository <Book, Long>{
	
	List<Book> findByname(String name);

}
