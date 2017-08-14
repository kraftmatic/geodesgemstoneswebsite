package com.kraftmatic.geodesgemstones.models;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.File;
import java.util.Date;

/**
 * Created by nkraftor on 2/9/17.
 */

@Entity
public class Article {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(nullable = false, name = "date")
    private Date date;
    @Column(nullable = false, name = "submitter")
    private String submitter;
    @Column(nullable = false, name = "content")
    private String content;
    @Transient
    private MultipartFile image;

    public Article() {
        date = new Date();
    }

    public Article(String name, String content) {
        submitter = name;
        this.content = content;
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitterName) {
        this.submitter = submitterName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
