package com.dartdirector.app.layers.repositories;

import com.dartdirector.app.entities.ScoreSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreSheetRepository extends JpaRepository<ScoreSheet, Long> {
}
