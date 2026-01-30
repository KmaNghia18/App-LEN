package com.nghia.applen.domain

import kotlinx.datetime.*

/**
 * Streak tracker for daily study habits
 */
class StreakTracker {
    
    /**
     * Calculate current streak based on study dates
     */
    fun calculateStreak(studyDates: List<Long>): StreakInfo {
        if (studyDates.isEmpty()) {
            return StreakInfo(
                currentStreak = 0,
                longestStreak = 0,
                lastStudyDate = null,
                isStudiedToday = false
            )
        }
        
        val sortedDates = studyDates.sorted().reversed() // Most recent first
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        
        var currentStreak = 0
        var longestStreak = 0
        var tempStreak = 0
        var previousDate: LocalDate? = null
        
        var isStudiedToday = false
        
        sortedDates.forEach { timestamp ->
            val date = Instant.fromEpochMilliseconds(timestamp)
                .toLocalDateTime(TimeZone.currentSystemDefault()).date
            
            // Check if studied today
            if (date == today && currentStreak == 0) {
                isStudiedToday = true
                currentStreak = 1
                tempStreak = 1
                previousDate = date
                return@forEach
            }
            
            if (previousDate == null) {
                // First date in the list
                previousDate = date
                tempStreak = 1
                if (currentStreak == 0) {
                    currentStreak = if (date == today || date == today.minus(1, DateTimeUnit.DAY)) {
                        1
                    } else {
                        0
                    }
                }
            } else {
                // Check if consecutive day
                val daysDiff = previousDate!!.toEpochDays() - date.toEpochDays()
                
                if (daysDiff == 1) {
                    tempStreak++
                    if (currentStreak > 0 || (date == today.minus(1, DateTimeUnit.DAY))) {
                        currentStreak++
                    }
                } else {
                    // Streak broken
                    if (tempStreak > longestStreak) {
                        longestStreak = tempStreak
                    }
                    tempStreak = 1
                }
                
                previousDate = date
            }
        }
        
        if (tempStreak > longestStreak) {
            longestStreak = tempStreak
        }
        
        return StreakInfo(
            currentStreak = currentStreak,
            longestStreak = maxOf(longestStreak, currentStreak),
            lastStudyDate = sortedDates.firstOrNull(),
            isStudiedToday = isStudiedToday
        )
    }
    
    /**
     * Check if user studied today
     */
    fun hasStudiedToday(studyDates: List<Long>): Boolean {
        if (studyDates.isEmpty()) return false
        
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        
        return studyDates.any { timestamp ->
            val date = Instant.fromEpochMilliseconds(timestamp)
                .toLocalDateTime(TimeZone.currentSystemDefault()).date
            date == today
        }
    }
    
    data class StreakInfo(
        val currentStreak: Int,
        val longestStreak: Int,
        val lastStudyDate: Long?,
        val isStudiedToday: Boolean
    )
}
