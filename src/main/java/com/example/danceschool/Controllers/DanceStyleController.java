package com.example.danceschool.Controllers;

import com.example.danceschool.Entities.DanceStyle;
import com.example.danceschool.Exceptions.ResourceNotFoundException;
import com.example.danceschool.Repositories.DanceStyleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/danceschool/styles")
public class DanceStyleController {
    @Autowired
    private DanceStyleRepo danceStyleRepo;

    @GetMapping("/all")
    public List<DanceStyle> getAll() {
        return danceStyleRepo.findAll();
    }

    @GetMapping("/dancestyles/{id}")
    public ResponseEntity<DanceStyle> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        DanceStyle danceStyle = danceStyleRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return ResponseEntity.ok().body(danceStyle);
    }

    @PostMapping("/dancestyles")
    public DanceStyle create(@Valid @RequestBody DanceStyle danceStyle) {
        return danceStyleRepo.save(danceStyle);
    }

    @PutMapping("/dancestyles/{id}")
    public ResponseEntity<DanceStyle> update(@PathVariable(value = "id") Integer id,
                                           @Valid @RequestBody DanceStyle styleDetails) throws ResourceNotFoundException {
        DanceStyle danceStyle = danceStyleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found for this id :: " + id));

        danceStyle.setName(styleDetails.getName());
        danceStyle.setDescription(styleDetails.getDescription());
        danceStyle.setOrigin(styleDetails.getOrigin());

        final DanceStyle updatedStyle = danceStyleRepo.save(danceStyle);
        return ResponseEntity.ok(updatedStyle);
    }

    @DeleteMapping("/dancestyles/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        DanceStyle danceStyle = danceStyleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found for this id :: " + id));

        danceStyleRepo.delete(danceStyle);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
