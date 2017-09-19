package com.kraftmatic.geodesgemstones.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    public void storeImage(MultipartFile imageFile) throws IOException;
}
