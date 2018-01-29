package org.superbiz.moviefun.albums;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {
    private AlbumsRepository albumsRepository;

    public AlbumsController(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    @PostMapping
    public void addAlbum(@RequestBody Album album) {
        this.albumsRepository.addAlbum(album);
    }

    @GetMapping("/{id}")
    public Album find(@PathVariable("id") Long id) {
        return this.albumsRepository.find(id);
    }

    @GetMapping
    public List<Album> getAlbums() {
        return this.albumsRepository.getAlbums();
    }

    @DeleteMapping
    public void deleteAlbum(Album album) {
        this.albumsRepository.deleteAlbum(album);
    }

    @PutMapping
    public void updateAlbum(Album album) {
        this.albumsRepository.updateAlbum(album);
    }
}
