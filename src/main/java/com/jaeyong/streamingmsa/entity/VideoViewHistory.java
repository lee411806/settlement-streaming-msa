package com.jaeyong.streamingmsa.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class VideoViewHistory extends UpdatedAtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long videoId;

    //MSA환경 문제로 User 대신 userid로 바꿈
    private Long userId;

    private Long currentPosition;


    public VideoViewHistory(Long userId, Long videoId, Long currentPosition) {
        this.userId = userId;
        this.videoId = videoId;
        this.currentPosition = currentPosition;
    }
}
