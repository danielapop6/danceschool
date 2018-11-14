package com.example.danceschool.Controllers;

import com.example.danceschool.Entities.Group;
import com.example.danceschool.Exceptions.ResourceNotFoundException;
import com.example.danceschool.Repositories.DanceStyleRepo;
import com.example.danceschool.Repositories.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/danceschool/groups")
public class GroupController {
    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private DanceStyleRepo danceStyleRepo;

    @GetMapping("/dancestyles/{id}/groups")
    public Page<Group> getAllByStyleId(@PathVariable(value = "id") Integer id,
                                       Pageable pageable) {
        return groupRepo.findByDanceStyleId(id, pageable);
    }

    @PostMapping("/dancestyles/groups")
    public Group create(@Valid @RequestBody Group group) {
        return groupRepo.save(group);

    }

    @PutMapping("/dancestyles/{danceId}/groups/{groupId}")
    public Group update(@PathVariable(value = "danceId") Integer danceId,
                        @PathVariable(value = "groupId") Integer groupId,
                        @Valid @RequestBody Group groupRequest) throws ResourceNotFoundException {
        if (!danceStyleRepo.existsById(danceId)) {
            throw new ResourceNotFoundException("Id " + danceId + " not found");
        }

        return groupRepo.findById(groupId).map(group -> {
            group.setLevel(groupRequest.getLevel());
            return groupRepo.save(group);
        }).orElseThrow(() -> new ResourceNotFoundException("Id " + groupId + "not found"));
    }

    @DeleteMapping("/dancestyles/{danceId}/groups/{groupId}")
    public ResponseEntity<?> delete(@PathVariable(value = "danceId") Integer danceId,
                                    @PathVariable(value = "groupId") Integer groupId) throws ResourceNotFoundException {
        if (!danceStyleRepo.existsById(danceId)) {
            throw new ResourceNotFoundException("Id " + danceId + " not found");
        }

        return groupRepo.findById(groupId).map(group -> {
            groupRepo.delete(group);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Id " + groupId + " not found"));
    }
}
