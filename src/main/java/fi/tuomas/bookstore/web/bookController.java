package fi.tuomas.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.tuomas.bookstore.domain.Book;
import fi.tuomas.bookstore.domain.BookRepository;
import fi.tuomas.bookstore.domain.CategoryRepository;

@Controller
public class bookController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value="/login")
	public String login() {
		return ("login");
	}

	@RequestMapping(value= {"/", "/booklist"})
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//REST to get all books
	
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}
	
	//REST to get book by id
	@RequestMapping(value="/book/{id}", method= RequestMethod.GET)
	public @ResponseBody Book findBookRest(@PathVariable("id") Long Bookid) {
		return repository.findByid(Bookid);
	}
	
	
	@GetMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}
	
	@PostMapping("/save")
	public String saveBook(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	@GetMapping(value= "/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("id")Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	@RequestMapping(value="edit/{id}")
	public String editBook(@PathVariable("id")Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}
}