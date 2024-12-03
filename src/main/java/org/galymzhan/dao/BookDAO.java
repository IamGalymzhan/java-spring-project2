package org.galymzhan.dao;

import org.galymzhan.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        String sql = "select * from book";
        return jdbcTemplate.query(sql, new BookMapper());
    }

    public List<Book> getBooksOfPerson(int personId) {
        String sql = "select * from book where person_id = ?";
        return jdbcTemplate.query(sql, new BookMapper(), personId);
    }

    public Book get(int bookId) {
        String sql = "select * from book where book_id = ?";
        return jdbcTemplate.queryForObject(sql, new BookMapper(), bookId);
    }

    public void insert(Book book) {
        String sql = "insert into book(title, author, year) values(?, ?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getPublicationYear());
    }

    public void update(int bookId, Book book) {
        String sql = "update book set title = ?, author = ?, year = ? where book_id = ?";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getPublicationYear(), bookId);
    }

    public void delete(int bookId) {
        String sql = "delete from book where book_id = ?";
        jdbcTemplate.update(sql, bookId);
    }

    public void free(int bookId) {
        String sql = "update book set person_id=null where book_id=?";
        jdbcTemplate.update(sql, bookId);
    }

    public void assign(int bookId, int personId) {
        String sql = "update book set person_id = ? where book_id = ?";
        jdbcTemplate.update(sql, personId, bookId);
    }
}
