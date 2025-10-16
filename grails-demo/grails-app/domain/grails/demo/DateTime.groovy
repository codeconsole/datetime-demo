package grails.demo

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime

import groovy.transform.TupleConstructor

@TupleConstructor   
class DateTime {
    Calendar calendar
    Date date
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
