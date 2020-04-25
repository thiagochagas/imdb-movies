package br.com.batata.movies.controller;

import br.com.batata.movies.entity.TitleBasics;
import br.com.batata.movies.entity.TitleRating;
import br.com.batata.movies.service.TitleBasicsService;
import br.com.batata.movies.service.TitleRatingService;
import br.com.batata.movies.vo.TitleBasicsVO;
import br.com.batata.movies.vo.TitleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "")
public class MovieController {

    private final TitleBasicsService titleBasicsService;
    private final TitleRatingService titleRatingService;

    @Autowired
    public MovieController(TitleBasicsService titleBasicsService,
                           TitleRatingService titleRatingService) {
        this.titleBasicsService = titleBasicsService;
        this.titleRatingService = titleRatingService;
    }

    /**
     * @param tconst
     * @return
     */
    @GetMapping("/rating/{tconst}")
    public ResponseEntity<TitleRating> findTitleRatingByTconst(@PathVariable(value = "tconst")
                                                                       String tconst) {
        return ResponseEntity.ok(titleRatingService.findByTconst(tconst));
    }

    /**
     * @param tconst
     * @return
     */
    @GetMapping("/basics/{tconst}")
    public ResponseEntity<TitleBasics> findTitleBasicsByTconst(@PathVariable(value = "tconst")
                                                                       String tconst) {
        return ResponseEntity.ok(titleBasicsService.findByTconst(tconst));
    }

    /**
     * @param minAverageRate
     * @return
     */
    @GetMapping("/movies/{minAverageRate}")
    public ResponseEntity<List<TitleVO>> findTitleBasicsByTconst(@PathVariable(value = "minAverageRate")
                                                                         Double minAverageRate) {
        List<TitleVO> listTitleVO = titleRatingService.findTconstWithAverageRateGreaterThan(minAverageRate);
        Map<String, TitleBasicsVO> titleBasicsList = titleBasicsService.findAllByTconstList(
                listTitleVO.stream().map(e -> e.getTconst()).collect(Collectors.toList())).
                stream().collect(Collectors.toMap(e -> e.getTconst(), e ->
                new TitleBasicsVO(e.getPrimaryTitle(), e.getOriginalTitle(), e.getGenres())));
        List<TitleVO> listWithRateWithoutBasics = new ArrayList<>();

        listTitleVO.stream().forEach(f -> {
            TitleBasicsVO titleBasicsVO = titleBasicsList.get(f.getTconst());
            if (titleBasicsVO != null) {
                f.setTitleBasicsVO(titleBasicsVO);
            } else {
                listWithRateWithoutBasics.add(f);
            }
        });
        listTitleVO.removeAll(listWithRateWithoutBasics);

        return ResponseEntity.ok(listTitleVO);
    }

}
