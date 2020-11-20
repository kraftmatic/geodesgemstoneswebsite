package com.kraftmatic.geodesgemstones.database;

import com.kraftmatic.geodesgemstones.models.Photo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PhotoRepository extends PagingAndSortingRepository<Photo, Long> {
    List<Photo> findPhotosByNameContains(String name);
}
