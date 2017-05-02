package dao;

import tables.Author;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;


@Stateless
public class AuthorDAO {
    @PersistenceContext
    EntityManager em;

    public List<Author> findAll(){
        return em.createQuery("select a from Author a").getResultList();
    }

    public Author addToDB(Author current) {
        em.persist(current);
        return current;
    }


    public Author findAuthorByID(int authorID) {
        return em.find(Author.class, authorID);
    }

    public void deleteAuthor(int id) {
            Author toDelete = em.find(Author.class, id);
            em.remove(toDelete);
    }
}
