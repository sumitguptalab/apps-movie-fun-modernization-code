package org.superbiz.moviefun.movies;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class Moviescontroller {

    private MoviesRepository moviesRepository;

    public Moviescontroller(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @GetMapping("/{id}")
    public Movie getMovieInfo(@PathVariable("id") Long id) {
        return this.moviesRepository.find(id);
    }

    @PostMapping
    public void addMovie(@RequestBody Movie movie) {
        this.moviesRepository.addMovie(movie);
    }

    @PutMapping
    public void updateMovie(@RequestBody Movie movie) {
        this.moviesRepository.updateMovie(movie);
    }

    @DeleteMapping
    public void deleteMovie(@RequestBody Movie movie) {
        this.moviesRepository.deleteMovie(movie);
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovieById(@PathVariable("movieId") Long movieId) {
        this.moviesRepository.deleteMovieId(movieId);
    }

    @GetMapping
    public List<Movie> findRange(@RequestParam(value = "field", required = false) String field,
                                 @RequestParam(value = "key", required = false) String searchTerm,
                                 @RequestParam(value = "start", required = false) Integer start,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (!StringUtils.isEmpty(field) && !StringUtils.isEmpty(searchTerm)) {
            return this.moviesRepository.findRange(field, searchTerm, start, pageSize);
        } else if (start != null && pageSize != null) {
            return this.moviesRepository.findAll(start, pageSize);
        }
        return this.moviesRepository.getMovies();
    }

    @GetMapping("/count")
    public int count(@RequestParam(value = "field", required = false) String field,
                     @RequestParam(value = "key", required = false) String searchTerm) {
        if (StringUtils.isEmpty(field) || StringUtils.isEmpty(searchTerm)) {
            return this.moviesRepository.countAll();
        }
        return this.moviesRepository.count(field, searchTerm);

    }
}
