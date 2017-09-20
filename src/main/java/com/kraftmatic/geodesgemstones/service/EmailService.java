package com.kraftmatic.geodesgemstones.service;

import com.kraftmatic.geodesgemstones.models.Contact;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface EmailService {

    void sendEmail(Contact email) throws UnirestException;

}
