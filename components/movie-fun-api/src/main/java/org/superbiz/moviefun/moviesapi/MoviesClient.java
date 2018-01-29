package org.superbiz.moviefun.moviesapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class MoviesClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String moviesUrl;
    private RestOperations restOperations;

    private static ParameterizedTypeReference<List<MovieInfo>> movieListType = new ParameterizedTypeReference<List<MovieInfo>>() {
    };


    public MoviesClient(String moviesUrl, RestOperations restOperations) {
        this.moviesUrl = moviesUrl;
        this.restOperations = restOperations;
    }

    public ResponseEntity<MovieInfo> find(Long id) {
        return this.restOperations.getForEntity(moviesUrl + "/" + id, MovieInfo.class);
    }

    public void addMovie(MovieInfo movie) {
        logger.debug("Creating movie with title {}, and year {}", movie.getTitle(), movie.getYear());
        this.restOperations.postForEntity(moviesUrl, movie, MovieInfo.class);
    }

    public void updateMovie(MovieInfo movie) {
        this.restOperations.put(moviesUrl, movie);
    }

    public void deleteMovie(MovieInfo movie) {
        this.restOperations.delete(moviesUrl, movie);
    }

    public void deleteMovieId(long id) {
        this.restOperations.delete(moviesUrl + "/" + id);
    }

    public List<MovieInfo> getMovies() {
        return this.restOperations.exchange(moviesUrl, GET, null, movieListType).getBody();
    }

    public List<MovieInfo> findAll(int firstResult, int maxResults) {
        String parameterizedUrl = moviesUrl + "?start=" + firstResult + "&pageSize=" + maxResults;
        return this.restOperations.exchange(parameterizedUrl, GET, null, movieListType).getBody();
    }

    public int countAll() {
        return this.restOperations.getForObject(moviesUrl + "/count", Integer.class);
    }

    public int count(String field, String searchTerm) {
        String parameterizedUrl = moviesUrl + "/count?field=" + field + "&key=" + searchTerm;
        return this.restOperations.getForObject(parameterizedUrl, Integer.class);
    }

    public List<MovieInfo> findRange(String field, String searchTerm, int firstResult, int maxResults) {
        String parameterizedUrl = moviesUrl + "?start=" + firstResult + "&pageSize=" + maxResults + "&field=" + field + "&key=" + searchTerm;
        return this.restOperations.exchange(parameterizedUrl, GET, null, movieListType).getBody();
    }

}
