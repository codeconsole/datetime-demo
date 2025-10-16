package grails.demo

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime

class DateTime {
    Calendar calendar
    Date date
    Instant instant
    LocalDate localDate
    LocalDateTime localDateTime
    OffsetDateTime offsetDateTime
    ZonedDateTime zonedDateTime

    DateTime(Calendar calendar, Date date, Instant instant, LocalDate localDate, LocalDateTime localDateTime,
             OffsetDateTime offsetDateTime, ZonedDateTime zonedDateTime) {
        this.calendar = calendar
        this.date = date
        this.instant = instant
        this.localDate = localDate
        this.localDateTime = localDateTime
        this.offsetDateTime = offsetDateTime
        this.zonedDateTime = zonedDateTime
    }
}
