package com.antoniofava.bookstoremanager.repository;

import com.antoniofava.bookstoremanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
