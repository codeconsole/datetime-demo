package grails.demo

import grails.converters.JSON
import grails.util.Metadata
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.ZoneId
import java.sql.Date as SqlDate
import java.sql.Time
import java.sql.Timestamp
import grails.plugin.scaffolding.annotation.Scaffold
import grails.util.Environment

@Scaffold(domain = DateTime)
class DateTimeController {

    def show(DateTime dateTime ) {
        dateTime = dateTime ?: buildDateTimeMap(request.format == 'gson')
        if (request.format == 'gson') {
            respond dateTime, view: 'gson'
        } else {
            respond dateTime
        }
    }

    def edit(DateTime dateTime) {
        respond dateTime ?: buildDateTimeMap(true)
    }

    private Map buildDateTimeMap(boolean safeForDefaultJson) {
        Map m = [:]

        def grailsVersion = Metadata.current.getGrailsVersion()
        m.platform = "Grails ${grailsVersion}".toString()

        def instant = Instant.parse("2025-10-08T07:48:46.407254Z")
        def zone = ZoneId.systemDefault()

        def date = Date.from(instant)
        def calendar = GregorianCalendar.from(ZonedDateTime.ofInstant(instant, zone))
        def sqlDate = new SqlDate(instant.toEpochMilli())
        def time = new Time(instant.toEpochMilli())
        def localTime = LocalTime.ofInstant(instant, zone)
        def timestamp = Timestamp.from(instant)
        def localDate = LocalDate.ofInstant(instant, zone)
        def localDateTime = LocalDateTime.ofInstant(instant, zone)
        def offsetDateTime = instant.atOffset(zone.rules.getOffset(instant))
        def zonedDateTime = instant.atZone(zone)

        if (safeForDefaultJson) {
            m.calendar = testSerialization(calendar)
        } else {
            m.calendar = calendar
        }

        m.date = date
        m.sqlDate = sqlDate
        m.time = time
        m.localTime = localTime
        m.timestamp = timestamp
        m.instant = instant
        m.localDate = localDate
        m.localDateTime = localDateTime
        m.offsetDateTime = offsetDateTime

        if (safeForDefaultJson) {
            m.zonedDateTime = testSerialization(zonedDateTime)
        } else {
            m.zonedDateTime = zonedDateTime
        }
        def dateTime = new DateTime(calendar, date, sqlDate, time, localTime, timestamp, instant, Environment.grailsVersion == '7.0.1-SNAPSHOT'? localDate : null, localDateTime, offsetDateTime, zonedDateTime)
//        def dateTime = new DateTime(calendar, date, sqlDate, time, instant, localDate, localDateTime, offsetDateTime, zonedDateTime)
        m.dateTime = dateTime

        return m
    }

    private def testSerialization(def value) {
        try {
            // Create a temporary map to test serialization
            def testMap = [test: value]
            def json = testMap as JSON
            json.toString()
            // If serialization succeeds, return the original value
            return value
        } catch (Exception e) {
            // If serialization fails, return an error message
            return "${value.class.simpleName} cannot be serialized by default JSON: ${e.message}"
        }
    }
}
