package com.github.valentina810.diaryofemotions.repository;

import com.github.valentina810.diaryofemotions.domain.model.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
    Optional<Emotion> findByName(String name);
}