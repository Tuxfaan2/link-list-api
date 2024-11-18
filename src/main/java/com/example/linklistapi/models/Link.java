package com.example.linklistapi.models;

import jakarta.persistence.*;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LINK_SEQ")
    @SequenceGenerator(name = "LINK_SEQ", sequenceName = "LINK_SEQ", initialValue = 0, allocationSize = 1)
    private Long id;
    private String url;
    private String title;
    private String description;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
