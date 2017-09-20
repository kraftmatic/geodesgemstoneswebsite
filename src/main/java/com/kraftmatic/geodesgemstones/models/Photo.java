package com.kraftmatic.geodesgemstones.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Photo {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;


    @Column(nullable = false, name = "date")
    private Date date;

    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = true, name = "region")
    private String region;
    @Column(nullable = true, name = "color")
    private String color;
    @Column(nullable = false, name = "category")
    private String category;
    @Column(nullable = false, name = "url")
    private String url;
    @Column(nullable = true, name = "comment")
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
