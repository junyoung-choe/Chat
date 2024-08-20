package com.example.back.global.filter;

import com.example.back.domain.member.entity.Member;
import com.example.back.util.jwt.repository.AuthRepository;
import com.example.back.util.response.ErrorCode;
import com.example.back.util.response.ErrorResponseEntity;
import com.example.back.util.response.exception.GlobalException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class MemberAuthenticationFilter implements Filter {

  private final AuthRepository authRepository;
  private final RedisTemplate<String, Long> redisTemplate;

  private final static List<URLMethod> whiteList = new ArrayList<>();

  static {
    //  key 값이 필요 없는 곳은 uri 추가
    whiteList.add(new URLMethod("/members", "POST"));
    whiteList.add(new URLMethod("/members/login", "GET"));
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest servletRequest = (HttpServletRequest) request;
    HttpServletResponse servletResponse = (HttpServletResponse) response;
    String requestURI = servletRequest.getRequestURI();
    String requestMethod = servletRequest.getMethod();
    log.info(requestURI + " : " + requestMethod);

    if (requestURI.equals("/api") || checkWhiteList(requestURI, requestMethod)) {
      chain.doFilter(request, response);
      return;
    }

    log.info("필터 진입");
    try {
      // 회원을 선별하는 UUID 값
      String token = servletRequest.getHeader("X-User-Id");
      log.info("X-User-Id 값 : " + token);
      if (!StringUtils.hasText(token)) {
        log.info("X-User-Id 값이 비어 있습니다.");
        throw new GlobalException(ErrorCode.KEY_NOT_FOUND);
      }

      Long memberId;
      // redis 접근
      memberId = redisTemplate.opsForValue().get(token);

      // DB 접근 (redis에 UUID로 만든 key가 없으면 DB에서 찾아오기 + redis에 저장하기)
      if (memberId == null) {
        log.info("redis에 UUID 가 존재하지 않아 DB를 탐색합니다");
        Member member = authRepository.findByToken(token).orElseThrow(
            () -> new GlobalException(ErrorCode.MEMBER_NOT_FOUND)).getMember();

        memberId = member.getId();
        redisTemplate.opsForValue().set(token, memberId);
      }

      servletRequest.setAttribute("memberId", memberId);
      log.info(" memberId : " + memberId);
      // 다음 필터 없으면 컨트롤러로 가겠지
      chain.doFilter(request, response);
    } catch (GlobalException e) {
      setErrorResponse(servletResponse, e.getErrorCode());
    }
  }

  private boolean checkWhiteList(String requestURI, String requestMethod) {
    for (URLMethod urlMethod : whiteList) {
      if (requestURI.startsWith(urlMethod.getUrl()) && requestMethod.equals(
          urlMethod.getMethod())) {
        return true;
      }
    }
    return false;
  }

  // make custom error
  private void setErrorResponse(HttpServletResponse response, ErrorCode ec) {
    ObjectMapper objectMapper = new ObjectMapper();
    response.setStatus(ec.getHttpStatus().value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8");
    ErrorResponseEntity errorResponseEntity = ErrorResponseEntity.builder()
        .statusCode(ec.getHttpStatus().value())
        .statusName(ec.name())
        .message(ec.getMessage())
        .build();
    try {
      response.getWriter().write(objectMapper.writeValueAsString(errorResponseEntity));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
