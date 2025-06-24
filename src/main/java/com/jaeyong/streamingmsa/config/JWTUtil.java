package com.jaeyong.streamingmsa.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class JWTUtil {

    private static final String COOKIE_NAME = "Authorization";

    //  쿠키에서 JWT 가져오는 메서드
    public String getTokenFromCookies(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue(); //  JWT 반환
                }
            }
        }
        return null; // JWT 쿠키가 없으면 null 반환
    }
}
