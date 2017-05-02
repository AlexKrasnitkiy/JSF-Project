package beans;

import dao.AuthorDAO;
import dao.BookDAO;
import tables.Author;
import tables.Book;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@SessionScoped
public class BookBean implements Serializable{
    @EJB
    BookDAO bookDAO;

    @EJB
    AuthorDAO authorDAO;

    private Book current;
    private int authorID;

    @PostConstruct
    public void init(){
        current = new Book();
    }

    public Book getBook() {
        return current;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public void setBoook(Book boook) {
        this.current = boook;
    }

    public List<Book> getBooks(){
        return bookDAO.showBooks();
    }

    public Object addToDB() {
        Author a = authorDAO.findAuthorByID(authorID);
        if (a == null){
            return null;
        }
        current.setAuthor(a);


        if (current.getTitle() == null || current.getTitle().isEmpty()){
            return "error";
        }
        bookDAO.addNewBook(current);
        current = new Book();
        return null;

    }

    public String addToDB(Author author) {
        current.setAuthor(author);
        bookDAO.addNewBook(current);
//        author.getBooks().add(book);
        current = new Book();
        return "booksbyauthor";
    }

}
