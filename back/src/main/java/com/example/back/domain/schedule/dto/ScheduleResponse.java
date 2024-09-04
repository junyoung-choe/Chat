package com.example.back.domain.schedule.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleResponse {
    private Long id;

    private String title;

    private String description;

    private Long priority;
}
