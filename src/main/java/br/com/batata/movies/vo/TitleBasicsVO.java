package br.com.batata.movies.vo;

public class TitleBasicsVO {

    public String primaryTitle;
    public String originalTitle;
    public String genres;

    public TitleBasicsVO(String primaryTitle, String originalTitle, String genres) {
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.genres = genres;
    }
}
