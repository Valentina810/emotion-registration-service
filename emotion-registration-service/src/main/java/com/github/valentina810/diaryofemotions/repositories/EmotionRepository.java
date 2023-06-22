package com.github.valentina810.diaryofemotions.repositories;

import com.github.valentina810.diaryofemotions.domain.model.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
}