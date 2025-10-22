package com.example.spring_demo;

import java.time.Instant;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;

public record DateTime(
    Calendar calendar,
    Date date,
    java.sql.Date sqlDate,
    Time time,
    LocalTime localTime,
    Timestamp Timestamp,
    Instant instant,
    LocalDate localDate,
    LocalDateTime localDateTime,
    OffsetDateTime offsetDateTime,
    ZonedDateTime zonedDateTime
) {}
