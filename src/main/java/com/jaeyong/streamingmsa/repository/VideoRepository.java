package com.jaeyong.streamingmsa.repository;



import com.jaeyong.streamingmsa.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Videos, Long> {
    @Query("SELECT v.duration FROM Videos v WHERE v.id = :videoId")
    Long findVideoLengthById(@Param("videoId") Long videoId);
}
