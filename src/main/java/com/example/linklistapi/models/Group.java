package com.example.linklistapi.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LINK_SEQ")
    @SequenceGenerator(name = "LINK_SEQ", sequenceName = "LINK_SEQ", initialValue = 0, allocationSize = 1)
    private Long id;
    private String name;
    private String description;

    @ElementCollection
    @CollectionTable(
            name = "group_users",
            joinColumns = @JoinColumn(name = "group_id")
    )
    @Column(name = "user_id", nullable = false)
    private List<String> users = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
