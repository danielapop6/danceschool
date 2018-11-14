package com.example.danceschool.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dancestyles")
public class DanceStyle {
    @Id
    @Column(name = "id", length = 10, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "origin", length = 30, nullable = false)
    private String origin;

    @Column(name = "description", length = 50)
    private String description;

    @OneToMany(mappedBy = "danceStyle",cascade = CascadeType.MERGE)
    private Set<Group> groups = new HashSet<>();

    public DanceStyle() {
    }

    public DanceStyle(String name, String origin, String description) {
        this.name = name;
        this.origin = origin;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "DanceStyle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
