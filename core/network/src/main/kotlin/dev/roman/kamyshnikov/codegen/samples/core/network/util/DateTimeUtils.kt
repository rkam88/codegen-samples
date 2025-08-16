package dev.roman.kamyshnikov.codegen.samples.core.network.util

import java.time.Instant
import java.time.OffsetDateTime
import java.time.YearMonth
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class DateTimeUtils @Inject constructor() {

    fun inBetween(
        unit: ChronoUnit,
        start: OffsetDateTime,
        end: OffsetDateTime
    ): Long {
        return unit.between(start, end)
    }

    fun minutesFromNow(
        target: OffsetDateTime
    ): Long {
        return inBetween(
            unit = ChronoUnit.MINUTES,
            start = OffsetDateTime.now(),
            end = target
        )
    }

    val now: ZonedDateTime
        get() = ZonedDateTime.now(ZoneId.systemDefault())

    fun now(zoneId: ZoneId): ZonedDateTime = ZonedDateTime.now(zoneId)

    fun formatHHMM(nextOpeningTime: OffsetDateTime): String {
        return DateTimeFormatter.ofPattern("HH:mm").format(nextOpeningTime)
    }

    fun formatDayOfTheWeek(dateTime: OffsetDateTime): String {
        return DateTimeFormatter.ofPattern("EEEE").format(dateTime)
    }

    fun formatFullDateMonth(date: OffsetDateTime): String {
        return DateTimeFormatter.ofPattern("d MMMM y").format(date)
    }

    fun from(lastTimeShown: Long): ZonedDateTime {
        val instant = Instant.ofEpochMilli(lastTimeShown)
        return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())
    }

    @Suppress("MagicNumber")
    fun formatOrdinalDay(day: Int): String {
        if (day < 1 || day > 31) throw kotlin.IllegalArgumentException("Bad day of month $day")
        val suffix = when (day) {
            1, 21, 31 -> "st"
            2, 22 -> "nd"
            3, 23 -> "rd"
            else -> "th"
        }
        return "$day$suffix"
    }

    /**
     * For the conversion we use ZoneOffset.UTC
     * If we use the current time zone for the conversion, it might show the wrong date:
     * - User selects his date of birth, which is returned as UTC milliseconds from the epoch
     *     (i.e. 1038441600000 -> 2002-11-28T00:00Z)
     * - If we're in a negative time zone, i.e. GMT-1 and we use the system's time zone, the result would be:
     *     2002-11-27T23:00-01:00
     */
    fun convertUtcMillis(utcMillis: Long): OffsetDateTime {
        val instant = Instant.ofEpochMilli(utcMillis)
        return OffsetDateTime.ofInstant(instant, ZoneOffset.UTC)
    }

    fun convertUtcMillisToZonedDateTime(utcMillis: Long, zoneId: ZoneId): ZonedDateTime {
        return Instant.ofEpochMilli(utcMillis).atZone(zoneId)
    }

    fun formatMonthYearToFullMonthYear(month: Int, year: Int, separated: Boolean): String {
        val yearMonth = YearMonth.of(year, month)
        val formatter =
            DateTimeFormatter.ofPattern("MMMM${",".takeIf { separated }.orEmpty()} yyyy")
        return yearMonth.format(formatter)
    }
}
