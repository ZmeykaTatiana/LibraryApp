package by.zmeyka.SpringMVC.DAO;

import by.zmeyka.SpringMVC.Model.Book;
import by.zmeyka.SpringMVC.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM library.book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM library.book WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO library.book (title,author) VALUES(?, ?)", book.getTitle(), book.getAuthor());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE library.book SET title=?  SEt author=? WHERE id=?",
                updatedBook.getTitle(),updatedBook.getAuthor(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM library.book WHERE id=?", id);
    }
    public Optional<Person> getBookBorrower(int id){
        return  jdbcTemplate.query("Select Person* from book join person on book.id_person=person.id"+
                "Where book.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();

    }
   public void release(int id){
        jdbcTemplate.update("Update book set id_person=NULL where id=?",id);

   }

   public void makeBorrowed(int id, Person person){
        jdbcTemplate.update("Update book set id_person=? where id=?",person.getId(),id);

   }

}
