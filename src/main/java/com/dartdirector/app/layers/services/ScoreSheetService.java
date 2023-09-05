package com.dartdirector.app.layers.services;

import com.dartdirector.app.entities.ScoreSheet;
import com.dartdirector.app.layers.repositories.ScoreSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreSheetService {

    @Autowired
    private ScoreSheetRepository scoreSheetRepository;

    public List<ScoreSheet> getAllScoreSheets() {
        return scoreSheetRepository.findAll();
    }

    public Optional<ScoreSheet> getScoreSheetById(Long id) {
        return scoreSheetRepository.findById(id);
    }

    public ScoreSheet saveScoreSheet(ScoreSheet scoreSheet) {
        return scoreSheetRepository.save(scoreSheet);
    }

    public void deleteScoreSheet(Long id) {
        scoreSheetRepository.deleteById(id);
    }
}
