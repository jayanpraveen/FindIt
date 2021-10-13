package me.realpraveen.book_detail_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.realpraveen.book_detail_service.Model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
