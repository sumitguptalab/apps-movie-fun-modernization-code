package org.superbiz.moviefun.albumsapi;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

public class AlbumInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String artist;
    private String title;
    private int year;
    private int rating;

    public AlbumInfo() {
    }

    public AlbumInfo(String artist, String title, int year, int rating) {
        this.artist = artist;
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean hasId() {
        return id != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumInfo albumInfo = (AlbumInfo) o;
        return getYear() == albumInfo.getYear() &&
                getRating() == albumInfo.getRating() &&
                Objects.equals(getId(), albumInfo.getId()) &&
                Objects.equals(getArtist(), albumInfo.getArtist()) &&
                Objects.equals(getTitle(), albumInfo.getTitle());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getArtist(), getTitle(), getYear(), getRating());
    }
}
