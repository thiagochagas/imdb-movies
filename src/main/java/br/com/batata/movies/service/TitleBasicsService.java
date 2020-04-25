package br.com.batata.movies.service;

import br.com.batata.movies.entity.TitleBasics;
import br.com.batata.movies.repository.TitleBasicsRepository;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TitleBasicsService {

    private static final String MOVIE = "movie";
    private static final int IN_CLAUSE_LIMIT_SIZE = 999;
    private TitleBasicsRepository titleBasicsRepository;

    @Autowired
    public TitleBasicsService(TitleBasicsRepository titleBasicsRepository){
        this.titleBasicsRepository = titleBasicsRepository;
    }

    public TitleBasics findByTconst(String tconst){
        return titleBasicsRepository.findByTconst(tconst).orElseThrow();
    }

    public List<TitleBasics> findAllByTconstList(List<String> tconstList){
        return findByAllTconstListWithPartitionSupport(MOVIE, tconstList);
    }

    private List<TitleBasics> findByAllTconstListWithPartitionSupport(String gender, List<String> tconstList) {
        final List<List<String>> partitions = ListUtils.partition(tconstList, IN_CLAUSE_LIMIT_SIZE);
        List<TitleBasics> indicatorAverageSumVO = partitions.stream().
                map(e->titleBasicsRepository.findByTitleTypeAndTconstIn(gender, e)).
                flatMap(Collection::stream).
                collect(Collectors.toList());
        return indicatorAverageSumVO;
    }

}
