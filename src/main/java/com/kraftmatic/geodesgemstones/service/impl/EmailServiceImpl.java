package com.kraftmatic.geodesgemstones.service.impl;

import com.kraftmatic.geodesgemstones.models.Contact;
import com.kraftmatic.geodesgemstones.service.EmailService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    private String mailgunApi;
    private String mailgunDomain;

    public EmailServiceImpl(@Value("${mailgun.api}") String mailgunApi,
                            @Value("${mailgun.domain}") String mailgunDomain){
        this.mailgunApi = mailgunApi;
        this.mailgunDomain = mailgunDomain;
    }

    @Override
    public void sendEmail(Contact email) throws UnirestException {

        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + mailgunDomain + "/messages")
                .basicAuth("api", mailgunApi)
                .queryString("from", email.getName() + " " +email.getEmail())
                .queryString("to", "geodesgemstones@gmail.com")
                .queryString("subject", "Website Message")
                .queryString("text", email.getMessage())
                .asJson();
    }

}
