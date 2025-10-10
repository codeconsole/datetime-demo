package com.example.spring_demo;

import org.springframework.boot.SpringBootVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class DateTimeController {

    @GetMapping("/")
    public Map<String, Object> index() {
        Map<String, Object> m = new LinkedHashMap<>();

        Instant instant = Instant.parse("2025-10-08T07:48:46.407254Z");
        ZoneId zone = ZoneId.systemDefault();

        Date date = Date.from(instant);
        GregorianCalendar calendar = GregorianCalendar.from(ZonedDateTime.ofInstant(instant, zone));
        LocalDate localDate = LocalDate.ofInstant(instant, zone);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        OffsetDateTime offsetDateTime = instant.atOffset(zone.getRules().getOffset(instant));
        ZonedDateTime zonedDateTime = instant.atZone(zone);

        m.put("platform", "Spring Boot " + SpringBootVersion.getVersion());
        m.put("calendar", calendar);
        m.put("date", date);
        m.put("instant", instant);
        m.put("localDate", localDate);
        m.put("localDateTime", localDateTime);
        m.put("offsetDateTime", offsetDateTime);
        m.put("zonedDateTime", zonedDateTime);

        DateTime dateTime = new DateTime(calendar, date, instant, localDate, localDateTime, offsetDateTime, zonedDateTime);
        m.put("dateTime", dateTime);

        return m;
    }
}
