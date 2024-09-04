package com.example.back.domain.schedule.entity;

import com.example.back.domain.dates.entity.Dates;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dates_id")
    Dates dates;

    private String title;

    private String description;

    private Long priority;

    @Builder
    public Schedule(Long id, Dates dates, String title, String description, Long priority) {
        this.id = id;
        this.dates = dates;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }
}
