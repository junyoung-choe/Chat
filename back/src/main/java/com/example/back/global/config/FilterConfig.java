package com.example.back.global.config;

import com.example.back.global.filter.MemberAuthenticationFilter;
import com.example.back.util.jwt.repository.AuthRepository;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MemberAuthenticationFilter> memberAuthenticationFilter
            (AuthRepository authRepository, RedisTemplate<String, Long> redisTemplate) {
        FilterRegistrationBean<MemberAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MemberAuthenticationFilter(authRepository, redisTemplate));
        registrationBean.addUrlPatterns("/*"); // 모든 URL에 필터를 적용
        return registrationBean;
    }
}