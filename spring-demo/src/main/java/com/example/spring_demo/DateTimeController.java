package com.example.spring_demo;

import org.springframework.boot.SpringBootVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;
import java.sql.Time;
import java.sql.Timestamp;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

@RestController
public class DateTimeController {

    @GetMapping("/")
    public Map<String, Object> index() throws DatatypeConfigurationException {
        Map<String, Object> m = new LinkedHashMap<>();

        Instant instant = Instant.parse("2025-10-08T07:48:46.407254Z");
        ZoneId zone = ZoneId.systemDefault();

        Date date = Date.from(instant);
        GregorianCalendar calendar = GregorianCalendar.from(ZonedDateTime.ofInstant(instant, zone));
        LocalDate localDate = LocalDate.ofInstant(instant, zone);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalTime localTime = LocalTime.ofInstant(instant, zone);
        OffsetDateTime offsetDateTime = instant.atOffset(zone.getRules().getOffset(instant));
        ZonedDateTime zonedDateTime = instant.atZone(zone);
        java.sql.Date sqlDate = new java.sql.Date(instant.toEpochMilli());
        Time time = new Time(instant.toEpochMilli());
        Timestamp timestamp = Timestamp.from(instant);

        m.put("platform", "Spring Boot " + SpringBootVersion.getVersion());
        m.put("calendar", calendar);
        m.put("date", date);
        m.put("sqlDate", sqlDate);
        m.put("time", time);
        m.put("localTime", localTime);
        m.put("timestamp", timestamp);
        m.put("instant", instant);
        m.put("localDate", localDate);
        m.put("localDateTime", localDateTime);
        m.put("offsetDateTime", offsetDateTime);
        m.put("zonedDateTime", zonedDateTime);

        m.put("offsetTime", offsetDateTime.toOffsetTime());
        m.put("year", Year.from(localDate));
        m.put("yearMonth", YearMonth.from(localDate));
        m.put("monthDay", MonthDay.from(localDate));
        m.put("month", localDate.getMonth());
        m.put("dayOfWeek", localDate.getDayOfWeek());
        m.put("duration", Duration.ofMinutes(90).plusMillis(250));
        m.put("period", Period.of(1, 2, 3));
        m.put("zoneId", zone);
        m.put("zoneOffset", offsetDateTime.getOffset());
        m.put("timeZone", TimeZone.getTimeZone(zone));
        m.put("xmlGregorianCalendar", DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
        m.put("xmlDuration", DatatypeFactory.newInstance().newDuration("P1DT2H"));
        Map<Object, String> dateKeys = new LinkedHashMap<>();
        dateKeys.put(date, "date");
        dateKeys.put(localDate, "localDate");
        dateKeys.put(zonedDateTime, "zonedDateTime");
        m.put("dateKeys", dateKeys);

        DateTime dateTime = new DateTime(DateTimeType.JAVA8, calendar, date, sqlDate, time, localTime, timestamp, instant, localDate, localDateTime, offsetDateTime, zonedDateTime);
        m.put("dateTime", dateTime);

        return m;
    }
}
