package com.kraftmatic.geodesgemstones.service;

import com.kraftmatic.geodesgemstones.models.Photo;
import com.kraftmatic.geodesgemstones.models.PhotoSubmission;
import com.kraftmatic.geodesgemstones.models.PhotoUpdate;

import java.io.IOException;

public interface ImageService {
    void storeImage(PhotoSubmission photoSubmit) throws IOException;
    void updateEntry(PhotoUpdate photoUpdate);
    Photo retrievePhotoInfoById(Long photoId);
}
