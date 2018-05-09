package com.kraftmatic.geodesgemstones.service;

import com.kraftmatic.geodesgemstones.models.Photo;
import com.kraftmatic.geodesgemstones.models.PhotoSubmission;

import java.io.IOException;

public interface ImageService {
    void storeImage(PhotoSubmission photoSubmit) throws IOException;
    Photo retrievePhotoInfoById(Long photoId);
}
