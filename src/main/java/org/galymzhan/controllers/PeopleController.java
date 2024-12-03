package org.galymzhan.controllers;

import jakarta.validation.Valid;
import org.galymzhan.dao.BookDAO;
import org.galymzhan.dao.PersonDAO;
import org.galymzhan.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    PersonDAO personDAO;
    BookDAO bookDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @PostMapping()
    public String insertPerson(@ModelAttribute("people") @Valid Person person) {
        personDAO.insert(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Person person = personDAO.get(id);
        model.addAttribute("person", person);
        model.addAttribute("books", bookDAO.getBooksOfPerson(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String addPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.get(id));
        return "people/edit";
    }

    @PostMapping("/{id}/edit")
    public String updatePerson(@ModelAttribute("person") @Valid Person person, @PathVariable("id") int id ,BindingResult result, Model model) {
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @PostMapping("/{id}/delete")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
