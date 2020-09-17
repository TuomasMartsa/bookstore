package fi.tuomas.bookstore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name, author, isbn;
	private int price, year;
	
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "categoryid")
	private Category category;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Book(String name, String author, String isbn, int price, int year, Category category) {
		super();
		this.name = name;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.year = year;
		this.category= category;

	}

	public Book() {

	}

	@Override
	public String toString() {
		if (this.category != null)
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", isbn=" + isbn + ", price=" + price
				+ ", year=" + year + ", category=" + this.getCategory() + "]";
		else
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", isbn=" + isbn + ", price=" + price
						+ ", year=" + year + "]";
	}

	
	

}
