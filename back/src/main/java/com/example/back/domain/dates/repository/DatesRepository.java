package com.example.back.domain.dates.repository;

import com.example.back.domain.dates.entity.Dates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatesRepository extends JpaRepository<Dates ,Long> {
}
