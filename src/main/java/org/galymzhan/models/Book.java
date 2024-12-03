package org.galymzhan.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;
    private int personId;

    @Size(min = 1, max = 50)
    private String title;

    @Size(min = 1, max = 50)
    private String author;

    @Min(value=0)
    @Max(value=2024)
    private int publicationYear;

    public Book() {
    }

    public Book(int id, int person_id, String title, String author, int yearOfPublication) {
        this.id = id;
        this.personId = person_id;
        this.title = title;
        this.author = author;
        this.publicationYear = yearOfPublication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int person_id) {
        this.personId = person_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
}
