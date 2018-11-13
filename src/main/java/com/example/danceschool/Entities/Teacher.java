package com.example.danceschool.Entities;

import javax.persistence.*;

@Entity
@Table(name = "teachers", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Teacher {

    @Id
    @Column(name = "id", length = 11, nullable = false, unique = true)
    private String id;

    @Column(name = "name", length = 20, nullable = true)
    private String name;

    @Column(name = "contact", length = 10, nullable = true)
    private String contact;

    public Teacher() {
    }

    public Teacher(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public Teacher(String id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact=" + contact +
                '}';
    }
}
