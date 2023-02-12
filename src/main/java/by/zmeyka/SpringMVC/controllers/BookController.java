package by.zmeyka.SpringMVC.controllers;


import by.zmeyka.SpringMVC.DAO.BookDAO;
import by.zmeyka.SpringMVC.DAO.PersonDAO;
import by.zmeyka.SpringMVC.Model.Book;
import by.zmeyka.SpringMVC.Model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookDAO bookDAO;
    private PersonDAO personDAO;

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }
   @GetMapping()
    public String index(Model model){
        model.addAttribute("book", bookDAO.index());
        return "book/index";


    }

    @GetMapping("/{id}")
    public String show(@PathVariable ("id") int id, Model model, @ModelAttribute ("person")Person person){
        model.addAttribute("book",bookDAO.show(id));
        Optional<Person> bookBorrower = bookDAO.getBookBorrower(id);
        if(bookBorrower.isPresent()){
            model.addAttribute("borrower", bookBorrower.get());
        }else{
            model.addAttribute("people",personDAO.index() );
        }
        return "book/show";

    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "book/new";

    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/new";
        }
        bookDAO.save(book);
        return "redirect:/books";

    }

}
