package org.superbiz.moviefun.albumsapi;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.superbiz.moviefun.blobstore.Blob;
import org.superbiz.moviefun.blobstore.BlobStore;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static java.lang.String.format;

public class CoverCatalog {

    private final BlobStore blobStore;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CoverCatalog(BlobStore blobStore) {
        this.blobStore = blobStore;
    }

    protected void tryToUploadCover(Long albumId, MultipartFile uploadedFile) throws IOException {
        Blob coverBlob = new Blob(
                getCoverBlobName(albumId),
                uploadedFile.getInputStream(),
                uploadedFile.getContentType()
        );

        blobStore.put(coverBlob);
    }

    Blob buildDefaultCoverBlob(long albumId) {
        return buildDefaultCoverBlob();
    }


    private Blob buildDefaultCoverBlob() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream input = classLoader.getResourceAsStream("default-cover.jpg");

        return new Blob("default-cover", input, MediaType.IMAGE_JPEG_VALUE);
    }

    private String getCoverBlobName(long albumId) {
        return format("covers/%d", albumId);
    }

    @HystrixCommand(fallbackMethod = "buildDefaultCoverBlob")
    protected Blob getCoverBlob(long albumId) throws IOException {
        Optional<Blob> maybeCoverBlob = blobStore.get(getCoverBlobName(albumId));
        return maybeCoverBlob.orElseGet(this::buildDefaultCoverBlob);
    }
}
