package dao;

import exceptions.Exceptions;
import model.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
@Transactional
public class BookDao extends GenericDao<Book, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public void updateISBN(Long id, String newISBN) {
        if (isIsbnValidFormat(newISBN) && isIsbnValidLength(newISBN)) {
            Book book = entityManager.find(Book.class, id);
            book.setISBN(newISBN);
            entityManager.persist(book);
        } else {
            throw new Exceptions(Exceptions.WRONG_ISBN_FORMAT);
        }
    }

    private boolean isIsbnValidFormat(String ISBN) {
        final String REGEX = "^(\\d{13}|\\s*)?$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(ISBN);
        return matcher.matches();
    }

    private boolean isIsbnValidLength(String ISBN) {
        return ISBN.length() == 13;
    }
}
