package com.jaeyong.streamingmsa.repository;



import com.jaeyong.streamingmsa.entity.DailyVideoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyVideoViewRepository extends JpaRepository<DailyVideoView, Long> {

}
