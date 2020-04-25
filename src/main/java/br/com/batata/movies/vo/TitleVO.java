package br.com.batata.movies.vo;

public class TitleVO {

    public String tconst;
    public Double averageRating;
    public Double numVotes;
    public TitleBasicsVO titleBasicsVO;

    public TitleVO(String tconst, Double averageRating, Double numVotes ){
        this.tconst = tconst;
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }

    public String getTconst() {
        return tconst;
    }

    public void setTitleBasicsVO(TitleBasicsVO titleBasicsVO) {
        this.titleBasicsVO = titleBasicsVO;
    }
}
