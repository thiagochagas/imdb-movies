package br.com.batata.movies.repository;

import br.com.batata.movies.entity.TitleBasics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitleBasicsRepository extends MongoRepository<TitleBasics, String> {

    Optional<TitleBasics> findByTconst(String tconst);
    List<TitleBasics> findByTitleTypeAndTconstIn(String titleType, List<String> tConstList);
}
