package repository;

import model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAllOrOrderByAuthor(String author);

    @Query("SELECT b.title FROM Book b WHERE b.isAvailable=true")
    List<Book> findAllByAvailableTrue();

    @Query("SELECT b.title FROM Book b WHERE length(b.title) >= 30")
    List<Book> findAllByTitleGreaterThan();

    List<Book> findAllByTitleContains(String title);
}
