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
    LocalDate localDate
    LocalDateTime localDateTime
    OffsetDateTime offsetDateTime
    ZonedDateTime zonedDateTime
}
