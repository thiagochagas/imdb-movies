package br.com.batata.movies.repository;

import br.com.batata.movies.entity.TitleRating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitleRatingRepository extends MongoRepository<TitleRating, String> {

    Optional<TitleRating> findByTconst(String tconst);
    List<TitleRating> findByAverageRatingGreaterThanAndNumVotesGreaterThan(Double averageRating, Double numVotes);
}
