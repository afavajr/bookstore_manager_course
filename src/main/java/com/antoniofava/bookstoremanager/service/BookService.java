package com.antoniofava.bookstoremanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniofava.bookstoremanager.dto.AuthorDTO;
import com.antoniofava.bookstoremanager.dto.BookDTO;
import com.antoniofava.bookstoremanager.dto.MessageResponseDTO;
import com.antoniofava.bookstoremanager.entity.Author;
import com.antoniofava.bookstoremanager.entity.Book;
import com.antoniofava.bookstoremanager.exception.BookNotFoundException;
// import com.antoniofava.bookstoremanager.mapper.BookMapper;
import com.antoniofava.bookstoremanager.repository.BookRepository;

@Service
public class BookService {

	private BookRepository bookRepository;
	
	// private final BookMapper bookMapper = BookMapper.INSTANCE;
	
	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public MessageResponseDTO create(BookDTO bookDTO) {
		Author authorToSave = Author.builder()
				.name(bookDTO.getAuthor().getName())
				.age(bookDTO.getAuthor().getAge())
				.build();
		Book bookToSave = Book.builder() // bookMapper.toModel(bookDTO)
				.name(bookDTO.getName())
				.pages(bookDTO.getPages())
				.chapters(bookDTO.getChapters())
				.isbn(bookDTO.getIsbn())
				.publisherName(bookDTO.getPublisherName())
				.author(authorToSave)
				.build();
		Book savedBook = bookRepository.save(bookToSave);
		return MessageResponseDTO.builder()
				.message("Book Created with ID " + savedBook.getId())
				.build();		
	}
	
	public BookDTO findById(Long id) throws BookNotFoundException {
		 Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
		 
		 AuthorDTO authorDTO = AuthorDTO.builder()
				.id(book.getAuthor().getId())
				.name(book.getAuthor().getName())
				.age(book.getAuthor().getAge())
				.build();
		 BookDTO bookDTO = BookDTO.builder()
				 .id(book.getId())
				 .name(book.getName())
				 .pages(book.getPages())
				 .chapters(book.getChapters())
				 .isbn(book.getIsbn())
				 .publisherName(book.getPublisherName())
				 .author(authorDTO)
				 .build();
		 return bookDTO; // bookMapper.toDTO(book)
	}
}
