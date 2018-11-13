package com.example.danceschool.Controller;

import com.example.danceschool.Entities.Teacher;
import com.example.danceschool.Exceptions.ResourceNotFoundException;
import com.example.danceschool.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/teachers")
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return ResponseEntity.ok().body(teacher);
    }

    @PostMapping("/teachers")
    public Teacher createTeacher(@Valid @RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable(value = "id") String id,
                                                 @Valid @RequestBody Teacher teacherDetails) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found for this id :: " + id));

        teacher.setName(teacherDetails.getName());
        teacher.setContact(teacherDetails.getContact());
        final Teacher updatedTeacher = teacherRepository.save(teacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/teachers/{id}")
    public Map<String, Boolean> deleteTeacher(@PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found for this id :: " + id));

        teacherRepository.delete(teacher);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
