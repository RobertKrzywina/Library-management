package dao;

import model.Book;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class BookDao extends GenericDao<Book, Long> {

}
