package com.jaeyong.streamingmsa.service;



import com.jaeyong.streamingmsa.config.JWTUtil;
import com.jaeyong.streamingmsa.dto.PauseResponse;
import com.jaeyong.streamingmsa.dto.PlayRequest;
import com.jaeyong.streamingmsa.dto.PlayResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final AbusePreventionHelper abusePreventionHelper; // 어뷰징 헬퍼
    private final VideoViewHelper videoViewHelper; // 조회수 헬퍼
    private final IpHelper ipHelper; // IP 헬퍼
    private final JWTUtil jwtUtil;


    // 동영상 재생 서비스
    public PlayResponse play(Long videoId, HttpServletRequest httpServletRequest, PlayRequest playRequest) {
        String jwtToken = jwtUtil.getTokenFromCookies(httpServletRequest);
        String ipAddress = ipHelper.getClientIp(httpServletRequest); // 헬퍼 사용


        videoViewHelper.createDailyVideoView(videoId, playRequest); // 헬퍼로 시청 기록 생성
        return new PlayResponse(true, "시청 기록 저장 완료");


        //JMeter 테스트로 인한 어뷰징 기능 해제
//        if (abusePreventionHelper.isAbusiveRequest(jwtToken, ipAddress)) { // 어뷰징 체크
//            return new PlayResponse(false, "어뷰징 요청입니다.");
//        } else {
//            videoViewHelper.createDailyVideoView(videoId, playRequest); // 헬퍼로 시청 기록 생성
//            return new PlayResponse(true, "시청 기록 저장 완료");
//        }

    }

    // 정지(Pause) 메서드
    public PauseResponse pause(Long userId, Long videoId, Long currentPosition) {

         return videoViewHelper.createVideoViewHistory(userId, videoId, currentPosition);
    }

}