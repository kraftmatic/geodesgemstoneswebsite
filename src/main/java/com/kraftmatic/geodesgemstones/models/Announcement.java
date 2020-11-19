package com.kraftmatic.geodesgemstones.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Announcement{

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(nullable = false, name = "announcement")
    private String announcement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnnouncement(){
	    return announcement;
    }

    public void setAnnouncement(String announcement){
	    this.announcement = announcement;
    }

}
