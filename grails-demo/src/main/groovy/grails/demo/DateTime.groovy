package grails.demo

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime

class DateTime {
    Instant instant
    Date date
    Calendar calendar
    LocalDate localDate
    LocalDateTime localDateTime
    OffsetDateTime offsetDateTime
    ZonedDateTime zonedDateTime

    DateTime(Instant instant, Date date, Calendar calendar, LocalDate localDate, LocalDateTime localDateTime,
             OffsetDateTime offsetDateTime, ZonedDateTime zonedDateTime) {
        this.instant = instant
        this.date = date
        this.calendar = calendar
        this.localDate = localDate
        this.localDateTime = localDateTime
        this.offsetDateTime = offsetDateTime
        this.zonedDateTime = zonedDateTime
    }
}
