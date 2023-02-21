package by.zmeyka.SpringMVC.controllers;


import by.zmeyka.SpringMVC.DAO.BookDAO;
import by.zmeyka.SpringMVC.DAO.PersonDAO;
import by.zmeyka.SpringMVC.Model.Book;
import by.zmeyka.SpringMVC.Model.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }
   @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "book/index2";


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
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/new";
        }
        bookDAO.save(book);
        return "redirect:/books";

    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id")  int id){
        model.addAttribute("book",bookDAO.show(id));
        return "book/edit";

    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book")@Valid Book book, BindingResult bindingResult,@PathVariable("id")int id){
        if(bindingResult.hasErrors()){
            return "book/edit";
        } bookDAO.update(id,book);
        return "redirect:/books";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        bookDAO.delete(id);
        return "redirect:/books";

    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id")int id){
        bookDAO.release(id);
        return "redirect:/books/"+id;

    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id")int id, @ModelAttribute("person") Person person){
        bookDAO.makeBorrowed(id,person);
        return "redirect:/books";

    }

}
