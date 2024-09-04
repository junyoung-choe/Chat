package com.example.back.domain.dates.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Dates {
    @Id
    @GeneratedValue
    @Column(name = "dates_id")
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDateTime todayDate;

    @Builder
    public Dates(String todayDate) {
        this.todayDate = LocalDateTime.parse(todayDate);
    }
}
