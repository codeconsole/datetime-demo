package grails.demo

import grails.converters.JSON
import grails.util.Metadata
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.time.ZoneId

class DateTimeController {

    static responseFormats = ['json']

    def index() {
        respond buildDateTimeMap(true)
    }

    def gson() {
        respond buildDateTimeMap(false)
    }

    private Map buildDateTimeMap(boolean safeForDefaultJson) {
        Map m = [:]

        def grailsVersion = Metadata.current.getGrailsVersion()
        m.platform = "Grails ${grailsVersion}".toString()

        def instant = Instant.parse("2025-10-08T07:48:46.407254Z")
        def zone = ZoneId.systemDefault()

        def date = Date.from(instant)
        def calendar = GregorianCalendar.from(ZonedDateTime.ofInstant(instant, zone))
        def localDate = LocalDate.ofInstant(instant, zone)
        def localDateTime = LocalDateTime.ofInstant(instant, zone)
        def offsetDateTime = instant.atOffset(zone.rules.getOffset(instant))
        def zonedDateTime = instant.atZone(zone)

        m.instant = instant
        m.date = date

        if (safeForDefaultJson) {
            m.calendar = testSerialization(calendar)
            m.zonedDateTime = testSerialization(zonedDateTime)
        } else {
            m.calendar = calendar
            m.zonedDateTime = zonedDateTime
        }

        m.localDate = localDate
        m.localDateTime = localDateTime
        m.offsetDateTime = offsetDateTime

        def dateTime = new DateTime(instant, date, calendar, localDate, localDateTime, offsetDateTime, zonedDateTime)
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
