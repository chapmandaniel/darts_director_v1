// src/main/java/com/dartdirector/app/repositories/TeamRepository.java

package com.dartdirector.app.layers.repositories;

import com.dartdirector.app.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    boolean existsByName(String name);
}


