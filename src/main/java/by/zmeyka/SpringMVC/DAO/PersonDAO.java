package by.zmeyka.SpringMVC.DAO;

import by.zmeyka.SpringMVC.Model.Book;
import by.zmeyka.SpringMVC.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;


@Component
public class PersonDAO {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate1) {
        this.jdbcTemplate = jdbcTemplate1;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM library.person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM library.person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO library.person(name,surname) VALUES(?, ?)", person.getName(), person.getSurname());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE library.person SET surname=?  WHERE id=?", updatedPerson.getSurname(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE  FROM library.person WHERE id=?", id);
    }

    public Optional<Person> getPersonBySurname(String surname){
        return  jdbcTemplate.query("Select*from library.person where surname=?",new Object[]{surname},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();

    }
    public List<Book> getBookbyIdPerson(int id){
        return jdbcTemplate.query("Select*from library.book where id_person=?",new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}



