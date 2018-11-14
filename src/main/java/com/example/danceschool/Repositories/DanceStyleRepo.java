package com.example.danceschool.Repositories;

import com.example.danceschool.Entities.DanceStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanceStyleRepo extends JpaRepository<DanceStyle,Integer> {
}
