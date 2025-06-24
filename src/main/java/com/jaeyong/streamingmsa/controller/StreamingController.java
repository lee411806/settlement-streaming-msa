package com.jaeyong.streamingmsa.controller;



import com.jaeyong.streamingmsa.dto.PauseResponse;
import com.jaeyong.streamingmsa.dto.PlayRequest;
import com.jaeyong.streamingmsa.dto.PlayResponse;
import com.jaeyong.streamingmsa.service.StreamingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StreamingController {

    private final StreamingService streamingService;

    //조회 수 증가 후 현재 재생시점 반환
    @PostMapping("/videos/{videoId}/play")
    public ResponseEntity<PlayResponse> play(@PathVariable Long videoId
            , HttpServletRequest httpServletRequest
            , @RequestBody PlayRequest playRequest) {

        PlayResponse result =  streamingService.play(videoId, httpServletRequest, playRequest);

        if (!result.isSuccess()) {
            return ResponseEntity
                    .badRequest()
                    .body(result);
        }

        return ResponseEntity.ok(result);
    }

    // 현재 재생 시점 db에 저장
    @PostMapping("/users/{userId}/videos/{videoId}/pause")
    public ResponseEntity pause(@PathVariable Long userId,
                      @PathVariable Long videoId,
                      @RequestParam Long currentPosition) {

        PauseResponse result = streamingService.pause(userId, videoId, currentPosition);
        if (!result.isSuccess()) {
            return ResponseEntity
                    .badRequest()
                    .body(result);
        }
        return ResponseEntity.ok(result);
    }
}
