package com.jaeyong.streamingmsa.repository;



import com.jaeyong.streamingmsa.entity.VideoViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoViewHistoryRepository extends JpaRepository<VideoViewHistory, Long> {
    Optional<VideoViewHistory> findByUserIdAndVideoId(Long userId, Long videoId);

}
