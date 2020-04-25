package br.com.batata.movies.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Comparator;

@Document(collection = "title.ratings")
public class TitleRating {

    @Id
    public String id;

    public String tconst;
    public Double averageRating;
    public Double numVotes;

    public static Comparator<TitleRating> getComparatorDesc() {
        return (o1, o2) -> {
            Double x1 = Double.valueOf(o1.averageRating);
            Double x2 = Double.valueOf(o2.averageRating);
            return x2.compareTo(x1);
        };
    }

    public String getTconst() {
        return tconst;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public Double getNumVotes() {
        return numVotes;
    }
}
