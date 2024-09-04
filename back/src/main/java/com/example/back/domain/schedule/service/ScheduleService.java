package com.example.back.domain.schedule.service;

import com.example.back.domain.dates.repository.DatesRepository;
import com.example.back.domain.schedule.entity.Schedule;
import com.example.back.domain.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final DatesRepository datesRepository;

    public Schedule getSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(()-> new RuntimeException("Schedule not found"));
    }

    public List<Schedule> getSchedules(Long datesId) {
        return scheduleRepository.findAllByDates(datesRepository.getReferenceById(datesId));
    }
}
