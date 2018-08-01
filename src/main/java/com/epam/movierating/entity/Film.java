package com.epam.movierating.entity;

import java.io.Serializable;
import java.util.List;

public class Film implements Serializable {
    private static final long serialVersionUID = -1726868805328879178L;
    private int id;
    private String name;
    private double raiting;
    private int yearOfIssue;
    private String director;
    private String description;
    private List<Genre> genres;

    public Film(){}

    public Film(int id, String name, double raiting, int yearOfIssue, String director, String description, List<Genre> genres) {
        this.id = id;
        this.name = name;
        this.raiting = raiting;
        this.yearOfIssue = yearOfIssue;
        this.director = director;
        this.description = description;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRaiting() {
        return raiting;
    }

    public void setRaiting(double raiting) {
        this.raiting = raiting;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (id != film.id) return false;
        if (Double.compare(film.raiting, raiting) != 0) return false;
        if (yearOfIssue != film.yearOfIssue) return false;
        if (name != null ? !name.equals(film.name) : film.name != null) return false;
        if (director != null ? !director.equals(film.director) : film.director != null) return false;
        if (description != null ? !description.equals(film.description) : film.description != null) return false;
        return genres != null ? genres.equals(film.genres) : film.genres == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(raiting);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + yearOfIssue;
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", raiting=" + raiting +
                ", yearOfIssue=" + yearOfIssue +
                ", director='" + director + '\'' +
                ", description='" + description + '\'' +
                ", genres=" + genres +
                '}';
    }
}
