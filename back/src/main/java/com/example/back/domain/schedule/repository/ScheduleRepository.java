package com.example.back.domain.schedule.repository;


import com.example.back.domain.dates.entity.Dates;
import com.example.back.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByDates(Dates dates);
}
