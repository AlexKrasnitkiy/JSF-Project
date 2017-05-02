package dao;

import tables.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by alexnap on 26.03.2017.
 */
@Stateless
public class BookDAO {
    @PersistenceContext
    EntityManager em;

    public List<Book> showBooks(){
        return em.createQuery("select b from Book b").getResultList();
    }

    public Book addNewBook(Book current) {
        em.persist(current);
        current.getAuthor().getBooks().add(current);
        em.merge(current.getAuthor());
        return current;
    }


}
