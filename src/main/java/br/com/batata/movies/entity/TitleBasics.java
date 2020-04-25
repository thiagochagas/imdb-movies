package br.com.batata.movies.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "title.basics")
public class TitleBasics {

    @Id
    public String id;

    public String tconst;
    public String titleType;
    public String  primaryTitle;
    public String  originalTitle;
    public String genres;

    public String getTconst() {
        return tconst;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getGenres() {
        return genres;
    }
}
