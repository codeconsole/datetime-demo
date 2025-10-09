package com.example.spring_demo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public record DateTime(
    Instant instant,
    Date date,
    Calendar calendar,
    LocalDate localDate,
    LocalDateTime localDateTime,
    OffsetDateTime offsetDateTime,
    ZonedDateTime zonedDateTime
) {}
