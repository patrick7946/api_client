package net.ibebu.user.common.data.enums

import net.ibebu.user.core.base.BaseEnum
import net.ibebu.user.core.enums.YesOrNo
import java.time.LocalDateTime
import java.time.ZoneOffset


enum class TimeZoneEnums(
    val code: String,
    val standardUtcOffset: Int,
    val daylightUtcOffset: Int,
    val daylightSavingYn: YesOrNo
) : BaseEnum<TimeZoneEnums> {
    PSTPDT("PST", -8, -7, YesOrNo.Y),
    CST("CST", -6, -6, YesOrNo.N),
    ESTEDT("EST", -5, -4, YesOrNo.Y),
    MSTMDT("MST", -7, -6, YesOrNo.Y),
    EST("EST", -5, -5, YesOrNo.N),
    MST("MST", -7, -7, YesOrNo.N),
    CSTCDT("CST", -6, -5, YesOrNo.Y),
    AKSTAKDT("AKST", -9, -8, YesOrNo.Y),
    HST("HST", -10, -10, YesOrNo.N);

    override val value = this
    companion object {
        fun convertToLocalDateTime(localDateTime: LocalDateTime): LocalDateTime {
            val currentTimeZone = getCurrentTimeZone(localDateTime)
            val utcOffset = if (currentTimeZone.daylightSavingYn == YesOrNo.Y) currentTimeZone.daylightUtcOffset else currentTimeZone.standardUtcOffset
            return localDateTime.plusHours(utcOffset.toLong())
        }

        private fun getCurrentTimeZone(localDateTime: LocalDateTime): TimeZoneEnums {
            return TimeZoneEnums.values().firstOrNull { timeZone ->
                val currentTime = localDateTime.atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneOffset.ofHours(timeZone.standardUtcOffset))
                timeZone.daylightSavingYn == YesOrNo.Y && isWithinDaylightSavingTime(currentTime.year, currentTime.monthValue, currentTime.dayOfMonth)
            } ?: TimeZoneEnums.values().first { it.standardUtcOffset == 0 }
        }

        private fun isWithinDaylightSavingTime(year: Int, month: Int, day: Int): Boolean {
            // 일광 절약 시간대 기간을 확인하는 로직을 구현해야 함
            // 이 예시에서는 간단한 로직으로 4월부터 10월까지 일광 절약 시간대로 간주
            return month in 4..10
        }
    }
}