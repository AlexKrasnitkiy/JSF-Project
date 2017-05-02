package beans;

import dao.AuthorDAO;
import tables.Author;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@Named
@SessionScoped
public class AuthorBean  implements Serializable{
    @EJB
    AuthorDAO authorDAO;

    private Author current;


    @PostConstruct
    public void init(){
        current = new Author();
    }

    public Author getAuthor() {
        return current;
    }

    public void setAuthor(Author author) {
        this.current = author;
    }

    public List<Author> getAuthors(){
        return authorDAO.findAll();
    }


    public Object addToDB() {
        if (current.getName() == null || current.getName().isEmpty()) {
            return "error";
        }
        authorDAO.addToDB(current);
        current = new Author();
        return null;
    }

    public Map<String, Integer> getOneAuthorMap(){
        Map<String, Integer> map = new TreeMap<>();
        for (Author author: getAuthors()){
            map.put(author.getName(), author.getId());

        }
        return map;
    }

    public String showBooks(Author a) {
        current = a;
        return "booksbyauthor";
    }

    public String deleteAuthor(int id) {
        try {
            authorDAO.deleteAuthor(id);
            return "index";
        } catch (Exception e) {
            return "error";
        }
        //        current = a;
//        authorDAO.deleteAuthor(current);
//        current = new Author();

    }
}
