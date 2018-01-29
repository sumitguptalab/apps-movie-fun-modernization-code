package org.superbiz.moviefun.albumsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class AlbumClient {
    private String albumsUrl;
    private RestOperations restOperations;
    private static ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
    };
    public AlbumClient(String albumsUrl, RestOperations restOperations) {
        this.albumsUrl = albumsUrl;
        this.restOperations = restOperations;
    }

    public void addAlbum(AlbumInfo albumInfo) {
        this.restOperations.postForEntity(albumsUrl, albumInfo, AlbumInfo.class);
    }

    public AlbumInfo find(long id) {
        return this.restOperations.getForObject(albumsUrl + "/" + id, AlbumInfo.class);
    }

    public List<AlbumInfo> getAlbums() {
        return this.restOperations.exchange(albumsUrl, GET, null,albumListType).getBody();
    }

    public void deleteAlbum(AlbumInfo albumInfo) {
        this.restOperations.delete(albumsUrl, albumInfo);
    }

    public void updateAlbum(AlbumInfo albumInfo) {
        this.restOperations.put(albumsUrl, albumInfo);
    }
}
