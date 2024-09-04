package com.example.back.domain.schedule.controller;

import com.example.back.domain.schedule.dto.ScheduleResponse;
import com.example.back.domain.schedule.entity.Schedule;
import com.example.back.domain.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 스케줄 단건 조회
    @GetMapping()
    ScheduleResponse getSchedule(Long schedule_id) {
        Schedule schedule = scheduleService.getSchedule(schedule_id);

        return ScheduleResponse.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .description(schedule.getDescription())
                .priority(schedule.getPriority())
                .build();

    }

    // 스케줄 하루 조회
    @GetMapping("/list")
    List<ScheduleResponse> getSchedules(Long dates_id) {
        List<Schedule> schedules = scheduleService.getSchedules(dates_id);
        List<ScheduleResponse> scheduleResponses = new ArrayList<>();

        for (Schedule schedule : schedules) {
            ScheduleResponse scheduleResponse = ScheduleResponse.builder()
                    .id(schedule.getId())
                    .title(schedule.getTitle())
                    .description(schedule.getDescription())
                    .priority(schedule.getPriority())
                    .build();
            scheduleResponses.add(scheduleResponse);
        }

        return scheduleResponses;
    }


}
