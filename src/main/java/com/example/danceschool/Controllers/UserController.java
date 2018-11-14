package com.example.danceschool.Controllers;

import com.example.danceschool.Entities.User;
import com.example.danceschool.Exceptions.ResourceNotFoundException;
import com.example.danceschool.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/danceschool/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/all")
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getById(@PathVariable(value = "id") String username) throws ResourceNotFoundException {
        User user = userRepo.findById(username).orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public User create(@Valid @RequestBody User user) {
        return userRepo.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable(value = "id") String username,
                                                 @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepo.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Not found for this id :: " + username));

        user.setPassword(userDetails.getPassword());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());

        final User updatedUser = userRepo.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") String username)
            throws ResourceNotFoundException {
        User user = userRepo.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Not found for this id :: " + username));

        userRepo.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
