package by.zmeyka.SpringMVC.controllers;


import by.zmeyka.SpringMVC.DAO.PersonDAO;
import by.zmeyka.SpringMVC.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private  PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.index());
        return "people/index1";


    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", personDAO.getBookbyIdPerson(id));
        return"people/show";

    }

   @GetMapping ("/new")
    public String newPerson (Model model){
        model.addAttribute("person", new Person());
        return "people/new";

   }

   @PostMapping()
  public String create(@ModelAttribute("person") Person person ) {
        personDAO.save(person);
        return "redirect/:people";

  }
   }

