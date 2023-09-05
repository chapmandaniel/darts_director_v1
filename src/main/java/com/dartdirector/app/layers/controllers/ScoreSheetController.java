package com.dartdirector.app.layers.controllers;

import com.dartdirector.app.entities.ScoreSheet;
import com.dartdirector.app.layers.services.ScoreSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scoresheets")
public class ScoreSheetController {

    @Autowired
    private ScoreSheetService scoreSheetService;

    @GetMapping
    public ResponseEntity<List<ScoreSheet>> getAllScoreSheets() {
        return ResponseEntity.ok(scoreSheetService.getAllScoreSheets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoreSheet> getScoreSheetById(@PathVariable Long id) {
        return scoreSheetService.getScoreSheetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ScoreSheet> createScoreSheet(@RequestBody ScoreSheet scoreSheet) {
        ScoreSheet savedScoreSheet = scoreSheetService.saveScoreSheet(scoreSheet);
        return ResponseEntity.ok(savedScoreSheet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScoreSheet> updateScoreSheet(@PathVariable Long id, @RequestBody ScoreSheet scoreSheet) {
        if (!scoreSheetService.getScoreSheetById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        scoreSheet.setId(id);
        return ResponseEntity.ok(scoreSheetService.saveScoreSheet(scoreSheet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScoreSheet(@PathVariable Long id) {
        if (!scoreSheetService.getScoreSheetById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        scoreSheetService.deleteScoreSheet(id);
        return ResponseEntity.noContent().build();
    }
}
