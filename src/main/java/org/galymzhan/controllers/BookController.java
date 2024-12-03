package org.galymzhan.controllers;

import org.galymzhan.dao.BookDAO;
import org.galymzhan.dao.PersonDAO;
import org.galymzhan.models.Book;
import org.galymzhan.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    PersonDAO personDAO;
    BookDAO bookDAO;

    @Autowired
    public BookController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @PostMapping()
    public String addBook(@ModelAttribute("book") Book book) {
        bookDAO.insert(book);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @GetMapping("/{id}")
    public String book(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.get(id));
        model.addAttribute("people", personDAO.index());
        if (bookDAO.get(id).getPersonId() != 0) {
            model.addAttribute("person", personDAO.get(bookDAO.get(id).getPersonId()));
        }
        else {
            model.addAttribute("person", new Person());
        }
        return "books/show";
    }

    @PostMapping("/{id}/edit")
    public String editBook(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.get(id);
        model.addAttribute("book", book);
        return "books/edit";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int id) {
        bookDAO.free(id);
        return "redirect:/books/" + id;
    }

    @PostMapping("/{id}/assign")
    public String assignBook(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        bookDAO.assign(id, person.getId());
        return "redirect:/books/" + id;
    }
}
