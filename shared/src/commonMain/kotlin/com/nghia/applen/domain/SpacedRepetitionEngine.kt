package com.nghia.applen.domain

import kotlin.math.max

/**
 * Spaced Repetition Engine using SM-2 Algorithm (SuperMemo 2)
 * 
 * Quality ratings:
 * 0 - Complete blackout
 * 1 - Incorrect response; correct one remembered
 * 2 - Incorrect response; correct one seemed easy to recall
 * 3 - Correct response recalled with serious difficulty
 * 4 - Correct response after hesitation
 * 5 - Perfect response
 */
class SpacedRepetitionEngine {
    
    companion object {
        private const val MIN_EFACTOR = 1.3f
        private const val INITIAL_EFACTOR = 2.5f
        private const val MILLIS_PER_DAY = 24 * 60 * 60 * 1000L
    }
    
    /**
     * Calculate next review based on SM-2 algorithm
     * 
     * @param quality Quality of response (0-5)
     * @param previousInterval Previous interval in days
     * @param previousEFactor Previous E-Factor
     * @param reviewCount Number of times reviewed
     * @return ReviewResult with next interval, new E-Factor, and next review date
     */
    fun calculateNextReview(
        quality: Int,
        previousInterval: Int = 0,
        previousEFactor: Float = INITIAL_EFACTOR,
        reviewCount: Int = 0
    ): ReviewResult {
        require(quality in 0..5) { "Quality must be between 0 and 5" }
        
        // Calculate new E-Factor
        val newEFactor = calculateEFactor(previousEFactor, quality)
        
        // Calculate interval
        val interval = when {
            quality < 3 -> {
                // Incorrect response: start over
                1
            }
            reviewCount == 0 -> {
                // First review
                1
            }
            reviewCount == 1 -> {
                // Second review
                6
            }
            else -> {
                // Subsequent reviews: multiply previous interval by E-Factor
                (previousInterval * newEFactor).toInt()
            }
        }
        
        val currentTime = System.currentTimeMillis()
        val nextReviewDate = currentTime + (interval * MILLIS_PER_DAY)
        
        return ReviewResult(
            nextInterval = interval,
            newEFactor = newEFactor,
            nextReviewDate = nextReviewDate,
            shouldReset = quality < 3
        )
    }
    
    /**
     * Calculate new E-Factor based on quality
     * Formula: EF' = EF + (0.1 - (5 - q) * (0.08 + (5 - q) * 0.02))
     */
    private fun calculateEFactor(previousEFactor: Float, quality: Int): Float {
        val newEFactor = previousEFactor + (0.1f - (5 - quality) * (0.08f + (5 - quality) * 0.02f))
        return max(newEFactor, MIN_EFACTOR)
    }
    
    /**
     * Get quality rating from simple know/don't know
     */
    fun getQualityFromResponse(knows: Boolean): Int {
        return if (knows) 5 else 0  // Perfect or complete blackout
    }
    
    /**
     * Check if card is due for review
     */
    fun isDue(nextReviewDate: Long): Boolean {
        return System.currentTimeMillis() >= nextReviewDate
    }
    
    data class ReviewResult(
        val nextInterval: Int,      // Interval in days
        val newEFactor: Float,       // New E-Factor
        val nextReviewDate: Long,    // Timestamp of next review
        val shouldReset: Boolean     // Whether to reset progress
    )
}
