package com.example.danceschool;

import com.example.danceschool.Controllers.DanceStyleController;
import com.example.danceschool.Controllers.GroupController;
import com.example.danceschool.Controllers.UserController;
import com.example.danceschool.Entities.DanceStyle;
import com.example.danceschool.Entities.Group;
import com.example.danceschool.Entities.User;
import com.example.danceschool.Enums.Level;
import com.example.danceschool.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DanceSchoolApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DanceSchoolApplication.class, args);
    }
    @Autowired
    private UserController userController;
    @Autowired
    private DanceStyleController styleController;
    @Autowired
    private GroupController groupController;
    @Override
    public void run(String... args) throws ResourceNotFoundException {

        User user2 = new User("carolina", "carolina", "Capitan", "Carolina", true);
        userController.create(user2);
        DanceStyle danceStyle = new DanceStyle("salsa","Spain","Funny, k");
        styleController.create(danceStyle);
        Group group = new Group(Level.BEGINNER,danceStyle);
        groupController.create(group);
    }
}
