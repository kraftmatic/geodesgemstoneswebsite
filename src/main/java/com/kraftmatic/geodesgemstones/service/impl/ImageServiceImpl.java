package com.kraftmatic.geodesgemstones.service.impl;

import com.kraftmatic.geodesgemstones.database.PhotoRepository;
import com.kraftmatic.geodesgemstones.models.Photo;
import com.kraftmatic.geodesgemstones.service.ImageService;
import com.kraftmatic.geodesgemstones.util.TokenHolder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@Service
public class ImageServiceImpl implements ImageService{

    private String imgurEndpoint;
    private String clientId;
    private String clientSecret;

    @Autowired
    private TokenHolder tokenHolder;

    @Autowired
    private PhotoRepository repository;

    public ImageServiceImpl(@Value("${imgur.endpoint.imageupload}") String imgurEndpoint,
                            @Value("${imgur.clientid}") String clientId,
                            @Value("${imgur.clientsecret}") String clientSecret){
        this.imgurEndpoint = imgurEndpoint;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public void storeImage(MultipartFile imageFile) throws IOException{
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + tokenHolder.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonObject = "{ \"image\" : \"" + new String(Base64.getEncoder().encodeToString(imageFile.getBytes())) + "\" }";

        HttpEntity<String> request = new HttpEntity<String>(jsonObject, headers);
        ResponseEntity<String> response = restTemplate.exchange( imgurEndpoint, HttpMethod.POST, request, String.class);
        JSONObject object = new JSONObject(response.getBody());
        String url = object.getJSONObject("data").getString("link");

        Photo photo = new Photo();
        photo.setCategory("test");
        photo.setComment("test");
        photo.setUrl(url);
        photo.setDate(new Date());

        repository.save(photo);

    }
}
