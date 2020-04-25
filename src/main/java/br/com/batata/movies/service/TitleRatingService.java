package br.com.batata.movies.service;

import br.com.batata.movies.entity.TitleRating;
import br.com.batata.movies.repository.TitleRatingRepository;
import br.com.batata.movies.vo.TitleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TitleRatingService {

    public static final Double MIN_NUM_VOTES = Double.valueOf("50000");
    private TitleRatingRepository titleRatingRepository;

    @Autowired
    public TitleRatingService(TitleRatingRepository titleRatingRepository){
        this.titleRatingRepository = titleRatingRepository;
    }

    public TitleRating findByTconst(String tconst){
        return titleRatingRepository.findByTconst(tconst).orElseThrow();
    }

    public List<TitleVO> findTconstWithAverageRateGreaterThan(Double averageRate){
        List<TitleRating> ratingList = titleRatingRepository.
                findByAverageRatingGreaterThanAndNumVotesGreaterThan(averageRate, MIN_NUM_VOTES);
        return ratingList.stream().sorted(TitleRating.getComparatorDesc()).map(n->
                new TitleVO(n.getTconst(),n.getAverageRating(),n.getNumVotes())).
                collect(Collectors.toList());
    }

}
