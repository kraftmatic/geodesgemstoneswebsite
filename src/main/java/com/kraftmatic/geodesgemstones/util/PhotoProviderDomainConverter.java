package com.kraftmatic.geodesgemstones.util;

import com.kraftmatic.geodesgemstones.models.Photo;
import com.kraftmatic.geodesgemstones.models.PhotoUpdate;

import java.util.Date;

public class PhotoProviderDomainConverter {

    public static Photo convertPhotoToDomain(PhotoUpdate update){

        Photo photo = new Photo();
        photo.setCategory(update.getCategory());
        photo.setColor(update.getColor());
        photo.setComment(update.getComment());
        photo.setDate(new Date());
        photo.setRegion(update.getRegion());
        photo.setUrl(update.getLink());
        photo.setId(update.getId());
        photo.setName(update.getName());
        return photo;
    }

    public static PhotoUpdate convertPhotoToProvider(Photo photo){
        PhotoUpdate update = new PhotoUpdate();
        update.setCategory(photo.getCategory());
        update.setColor(photo.getColor());
        update.setComment(photo.getComment());
        update.setId(photo.getId());
        update.setRegion(photo.getRegion());
        update.setLink(photo.getUrl());
        update.setName(photo.getName());
        return update;
    }

}
