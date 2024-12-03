package org.galymzhan.models;

import jakarta.validation.constraints.*;

public class Person {
    private int id;

    @Size(min = 2, max = 50)
    private String name;

    @Min(value=1900)
    @Max(value=2024)
    private int birthYear;

    public Person() {
    }

    public Person(int id, String name, int birthYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }
}
