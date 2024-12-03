package org.galymzhan.dao;

import org.galymzhan.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person", new PersonMapper());
    }

    public Person get(int id) {
        String sql = "select * from person where person_id=?";
        return jdbcTemplate.queryForObject(sql, new PersonMapper(), id);
    }

    public void insert(Person person) {
        String sql = "insert into person(name, birth_year) values(?, ?)";
        jdbcTemplate.update(sql, person.getName(), person.getBirthYear());
    }

    public void update(int id, Person person) {
        String sql = "update person set name=?, birth_year=? where person_id = ?";
        jdbcTemplate.update(sql, person.getName(), person.getBirthYear(), id);
    }

    public void delete(int id) {
        String sql = "delete from person where person_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
