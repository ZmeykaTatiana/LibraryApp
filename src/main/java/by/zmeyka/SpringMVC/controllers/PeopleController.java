package by.zmeyka.SpringMVC.controllers;


import by.zmeyka.SpringMVC.DAO.PersonDAO;
import by.zmeyka.SpringMVC.Model.Person;
import jakarta.validation.Valid;
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
        model.addAttribute("book", personDAO.getBookbyIdPerson(id));
        return"people/show";

    }

   @GetMapping  ("/new")
    public String newPerson (Model model){
        model.addAttribute("person", new Person());
        return "people/new";

   }

   @PostMapping()
  public String create(@ModelAttribute("person") @Valid Person person ) {
        personDAO.save(person);
        return "people/allGood";

  }

  @GetMapping("/{id}/edit")
  public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
  return "people/update";

  }
  @PostMapping ("/{id}")
    public String update(@ModelAttribute("person") Person person,@PathVariable("id")int id){
        personDAO.update(id,person);
        return "people/allGood";

  }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        personDAO.delete(id);
        return "people/allGood";

    }
   }

