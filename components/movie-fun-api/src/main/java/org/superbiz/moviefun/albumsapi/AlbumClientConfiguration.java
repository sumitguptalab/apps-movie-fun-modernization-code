package org.superbiz.moviefun.albumsapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.superbiz.moviefun.moviesapi.MoviesClient;

@Configuration
public class AlbumClientConfiguration {

    @Value("${albums.url}") String albumsUrl;

    @Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }

    @Bean
    public AlbumClient albumClient(RestOperations restOperations) {
        return new AlbumClient(albumsUrl, restOperations);
    }
}
