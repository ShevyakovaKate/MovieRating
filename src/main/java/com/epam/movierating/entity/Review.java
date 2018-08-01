package com.epam.movierating.entity;

import java.io.Serializable;
import java.util.Objects;

public class Review implements Serializable {
    private static final long serialVersionUID = 4995483246786093041L;
    private int id;
    private int userId;
    private String userName;
    private int filmId;
    private String content;
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFilmId() {
        return filmId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (id != review.id) return false;
        if (userId != review.userId) return false;
        if (filmId != review.filmId) return false;
        if (rating != review.rating) return false;
        if (userName != null ? !userName.equals(review.userName) : review.userName != null) return false;
        return content != null ? content.equals(review.content) : review.content == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + filmId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + rating;
        return result;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", filmId=" + filmId +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                '}';
    }
}
