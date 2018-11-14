package com.example.danceschool.Repositories;

import com.example.danceschool.Entities.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepo extends JpaRepository<Group,Integer> {
    Page<Group> findByDanceStyleId(Integer styleId, Pageable pageable);
}
