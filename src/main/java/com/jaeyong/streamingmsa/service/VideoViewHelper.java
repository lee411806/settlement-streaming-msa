package com.jaeyong.streamingmsa.service;


import com.jaeyong.streamingmsa.dto.PauseResponse;
import com.jaeyong.streamingmsa.dto.PlayRequest;
import com.jaeyong.streamingmsa.entity.DailyVideoView;
import com.jaeyong.streamingmsa.entity.VideoViewHistory;
import com.jaeyong.streamingmsa.repository.DailyVideoViewRepository;
import com.jaeyong.streamingmsa.repository.VideoRepository;
import com.jaeyong.streamingmsa.repository.VideoViewHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class VideoViewHelper {

    private final VideoRepository videoRepository;
    private final VideoViewHistoryRepository videoViewHistoryRepository;
    private final DailyVideoViewRepository dailyVideoViewRepository;

    @Transactional
    public void createDailyVideoView(Long videoId, PlayRequest playRequest) {

        // userId를 jwt 에서 추출해서 userid랑 videoid랑 통해서 currentposition 서버에서 추출함
        // currentposition가져와서 로그데이터에 넣기

        // DTO → Entity 변환
        DailyVideoView dailyVideoView = new DailyVideoView(playRequest, videoId);

        dailyVideoViewRepository.save(dailyVideoView);
    }


    // 동영상 정지 시 currentposition 저장 메서드
    @Transactional
    public PauseResponse createVideoViewHistory(Long userId, Long videoId, Long currentPosition) {
        Long videoLength = videoRepository.findVideoLengthById(videoId);

        if (videoLength == null) {
            throw new EntityNotFoundException("해당 Video가 존재하지 않습니다.");
        }

        //  비교 로직 유지
        if(!validateCurrentPosition(currentPosition, videoLength)){
            return new PauseResponse(false, "재생 위치가 영상 길이를 초과했습니다.");
        };

        VideoViewHistory history = videoViewHistoryRepository
                .findByUserIdAndVideoId(userId, videoId)
                .orElse(new VideoViewHistory(userId, videoId, currentPosition));

        history.setCurrentPosition(currentPosition);
        videoViewHistoryRepository.save(history);
        return new PauseResponse(true, "정지 위치 저장 완료");
    }

    // 현재 재생 위치가 영상 길이를 초과하는지 체크
    private boolean validateCurrentPosition(Long currentPosition, Long videoLength) {
        if (currentPosition > videoLength) {
            return false;
        }
        return true;
    }
}
