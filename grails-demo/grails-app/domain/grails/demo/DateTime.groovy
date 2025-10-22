package grails.demo

import java.time.Instant
import java.time.LocalTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.sql.Date as SqlDate
import java.sql.Time
import java.sql.Timestamp

import groovy.transform.TupleConstructor

@TupleConstructor   
class DateTime {
    Calendar calendar
    Date date
    SqlDate sqlDate
    Time time
    LocalTime localTime
    Timestamp timestamp 
    Instant instant
    LocalDate localDate    // causes exception on 7.0.0 and RC2
    LocalDateTime localDateTime
    OffsetDateTime offsetDateTime
    ZonedDateTime zonedDateTime

    static constraints = {
        calendar()
       instant nullable: true
       offsetDateTime nullable: true
       zonedDateTime nullable: true
    }
}
